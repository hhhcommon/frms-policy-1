package cn.com.bsfit.frms.policy.jms;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.com.bsfit.frms.obj.AuditObject;
import cn.com.bsfit.frms.obj.AuditResult;
import cn.com.bsfit.frms.obj.MemCachedItem;
import cn.com.bsfit.frms.obj.Risk;
import cn.com.bsfit.frms.policy.mapper.portal.ResourcesMapper;
import cn.com.bsfit.frms.policy.mapper.portal.RiskLevelMapper;
import cn.com.bsfit.frms.policy.mapper.rams.ChecklistHisMapper;
import cn.com.bsfit.frms.policy.mapper.rams.ChecklistMapper;
import cn.com.bsfit.frms.policy.mapper.rams.ChecklistRisksMapper;
import cn.com.bsfit.frms.policy.pojo.rams.Checklist;
import cn.com.bsfit.frms.policy.pojo.rams.ChecklistHis;
import cn.com.bsfit.frms.policy.pojo.rams.ChecklistRisks;

public class ChecklistCallable implements Callable<Integer> {

	
	private Session session;
	private String ramsJdbcType;
	private List<TextMessage> messages;
	private RiskLevelMapper riskLevelMapper;
	private ResourcesMapper resourcesMapper;
	private SqlSessionFactory sqlSessionFactory;
	private Logger logger = LoggerFactory.getLogger(ChecklistListener.class);

	public ChecklistCallable(Session session, String ramsJdbcType, RiskLevelMapper riskLevelMapper, 
			ResourcesMapper resourcesMapper, SqlSessionFactory sqlSessionFactory, List<TextMessage> messages) {
		this.session = session;
		this.ramsJdbcType = ramsJdbcType;
		this.messages = messages;
		this.riskLevelMapper = riskLevelMapper;
		this.resourcesMapper = resourcesMapper;
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Integer call() throws Exception {
		long begin = System.currentTimeMillis();
		if (messages == null || messages.size() == 0) {
			logger.warn("No messages available!");
			return 0;
		}
		SqlSession sqlSession;
		if (ramsJdbcType.equals("oracle")) {
			sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
		} else {
			sqlSession = sqlSessionFactory.openSession();
		}
		try {
			// 保存核查单
			for (TextMessage msg : messages) {
				// 队列里的消息的格式是：一个List集合，该集合里的每一个元素是由AuditResult,AuditObject,List<MemCachedItem>三部分组成的对象
				List<List<Object>> publishObjList = (List<List<Object>>) JSON.parse(msg.getText());
				if (publishObjList == null)
					continue;
				for (List<Object> publishObj : publishObjList) {
					if (publishObj != null && publishObj.size() >= 2) {
						AuditResult auditResult = (AuditResult) publishObj.get(0);
						AuditObject auditObject = (AuditObject) publishObj.get(1);
						List<MemCachedItem> mItems = (publishObj.size() > 2 && publishObj.get(2) != null) ? (List<MemCachedItem>) publishObj.get(2) : null;
						long checklistId = saveChecklist(auditObject, auditResult, sqlSession);
						// 保存规则触发信息
						saveChecklistRisks(auditResult, checklistId, sqlSession);
						// 保存核查单历史详情
						saveChecklistHis(auditObject, auditResult, checklistId, sqlSession);
						// 保存风险触发时的缓存状态，因为第一期行为信息暂时不做，保留方法 TODO
						saveMemCachedItem(mItems, sqlSession);
						sqlSession.commit();
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred when saving data", e);
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		long end = System.currentTimeMillis();
		logger.info("messages size:[{}], time:[{}]ms", messages.size(), end - begin);
		return null;
	}

	// 保存核查单
	public Long saveChecklist(AuditObject auditObject, AuditResult auditResult, SqlSession sqlSession) {

		Object transTimeObj = auditObject.get("frms_trans_time");
		Date transTime = (transTimeObj instanceof Long) ? new Date((Long) transTimeObj) : (Date) transTimeObj;

		Checklist checklist = new Checklist();
		checklist.setTransVol((Long) auditObject.get("frms_trans_vol")); // 交易金额
		checklist.setOrderId((String) auditObject.get("frms_trans_id")); // 交易单号
		checklist.setCreateTime(transTime); // 交易时间（核查单创建时间）
		checklist.setUpdateTime(transTime); // 交易时间（核查单更新时间）
		checklist.setPayUserId((String) auditObject.get("frms_user_id")); // 支付用户号
		checklist.setBizCode((String) auditObject.get("frms_biz_code")); // 业务类型代码
		checklist.setPayBankCardNo((String) auditObject.get("frms_bank_card_no")); // 银行卡号
		checklist.setRecTerminalId((String) auditObject.get("frms_machine_id")); // 终端编号
		checklist.setRecUserId((String) auditObject.get("frms_merchant_id")); // 商户号
		checklist.setRedundantFields1((String) auditObject.get("frms_user_login")); // 支付方用户姓名存到冗余字段1中
		checklist.setScore(auditResult.getScore());
		checklist.setRiskLevel(riskLevelMapper.getRiskLevel(auditResult.getScore()));
		checklist.setStatus(0); // 受理状态 默认待处理

		checklist.setVerifiStrategy(auditResult.getVerifyPolicy().getName()); // 验证策略
		checklist.setNotifyStrategy(auditResult.getNotifyPolicy().getName()); // 通知策略

		// 从拥有核查权限的用户中随机选择一名作为受理人员
		List<Long> userIds = resourcesMapper.selectUserByResourceCode("1010");
		if (userIds != null && !userIds.isEmpty()) {
			checklist.setOperUserId(userIds.get((int) (Math.random() * userIds.size())));
		} else {
			logger.warn("Couldn't find any user who have the check permission, checklist will be assigned to 'admin'");
			checklist.setOperUserId(1L); // 默认受理人员（处理人员）
		}
		ChecklistMapper checklistMapper = sqlSession.getMapper(ChecklistMapper.class);
		checklistMapper.insert(checklist);
		return checklist.getId();
	}

	// 保存规则触发信息
	public void saveChecklistRisks(AuditResult auditResult, long checklistId, SqlSession sqlSession) {
		if (auditResult.getRisks() == null) {
			return;
		}
		ChecklistRisksMapper checklistRisksMapper = sqlSession.getMapper(ChecklistRisksMapper.class);
		ChecklistMapper checklistMapper = sqlSession.getMapper(ChecklistMapper.class);
		// 去重风险类型用
		Set<String> riskTypes = new HashSet<String>();
		for (Risk risk : auditResult.getRisks()) {
			ChecklistRisks checklistRisks = new ChecklistRisks();
			checklistRisks.setChecklistId(checklistId);
			checklistRisks.setRuleName(risk.getRuleName());
			checklistRisks.setRulePackageName(risk.getRulePackageName());
			checklistRisks.setScore(risk.getScore());
			// 冗余风险类型进checklist_risks表
			checklistRisks.setRiskType(risk.getRiskTypes() == null ? null : JSON.toJSONString(risk.getRiskTypes()));
			if (risk.getRuleName() != null) {
				String[] ruleNames = risk.getRuleName().split(":");
				checklistRisks.setRuleId(ruleNames.length > 1 ? ruleNames[1].trim() : null);
			}
			// 风险类型以核查单为单位去重
			if (risk.getRiskTypes() != null) {
				riskTypes.addAll(risk.getRiskTypes());
			}
			checklistRisksMapper.insert(checklistRisks);
		}
		// 去重后的风险类型存入checklist_risk_type表,以便核查单列表页根据风险类型筛选数据
		for (String riskType : riskTypes) {
			checklistMapper.insertChecklistRiskType(checklistId, riskType);
		}
	}

	// 保存核查单历史详情
	public void saveChecklistHis(AuditObject auditObject, AuditResult auditResult, long checklistId,
			SqlSession sqlSession) {
		if (auditResult.getRisks() == null) {
			return;
		}
		ChecklistHisMapper checklistHisMapper = sqlSession.getMapper(ChecklistHisMapper.class);
		for (Risk risk : auditResult.getRisks()) {
			ChecklistHis checklistHis = new ChecklistHis();
			checklistHis.setChecklistId(checklistId);
			checklistHis.setPrimaryId(
					auditObject.get("frms_user_id") == null ? "" : auditObject.get("frms_user_id").toString());
			checklistHis.setCreateTime(new Date());
			checklistHis.setOperUserId(-1L);
			checklistHis.setDealContent(risk.getRuleName() + ", " + risk.getRulePackageName() + risk.getComments());
			checklistHis.setControl(risk.getVerifyPolicy().getFailControl());
			checklistHisMapper.insert(checklistHis);
		}
	}

	// 保存风险触发时的缓存状态，因为第一期行为信息暂时不做，保留方法 TODO
	public void saveMemCachedItem(List<MemCachedItem> mItems, SqlSession sqlSession) {

	}
}
