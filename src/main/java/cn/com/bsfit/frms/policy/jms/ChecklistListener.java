package cn.com.bsfit.frms.policy.jms;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

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
import cn.com.bsfit.frms.policy.utils.SessionAwareBatchMessageListener;

/**
 * 监听核查单队列里的消息
 * Created by 崇建 on 15-3-2.
 */
@Component
public class ChecklistListener extends MessageListenerAdapter implements SessionAwareBatchMessageListener<TextMessage> {

    private Logger logger = LoggerFactory.getLogger(ChecklistListener.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private RiskLevelMapper riskLevelMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;
    @Value("${rams.jdbc.type}")
    private String ramsJdbcType;

    @SuppressWarnings("unchecked")
    public void onMessages(Session session, List<TextMessage> messages) throws JMSException {

        if (messages == null || messages.size() == 0) {
            logger.warn("No messages available!");
            return;
        }
        logger.info("ChecklistListener received {} msgs", messages.size());
        logger.debug("ChecklistListener received msgs: {}", messages.toString());
        SqlSession sqlSession;
        if (ramsJdbcType.equals("oracle")) {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        } else {
            sqlSession = sqlSessionFactory.openSession();
        }

        try {
            // 保存核查单
            for (TextMessage msg : messages) {
                //队列里的消息的格式是：一个List集合，该集合里的每一个元素是由AuditResult,AuditObject,List<MemCachedItem>三部分组成的对象
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
                        logger.debug("Save the data {} successfully", publishObj);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("An error occurred when saving data", e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    // 保存核查单
    public Long saveChecklist(AuditObject auditObject, AuditResult auditResult, SqlSession sqlSession) {

        Object transTimeObj = auditObject.get("frms_trans_time");
        Date transTime = (transTimeObj instanceof Long) ? new Date((Long) transTimeObj) : (Date) transTimeObj;

        Checklist checklist = new Checklist();
        checklist.setTransVol((Long) auditObject.get("frms_trans_vol"));  //交易金额
        checklist.setOrderId((String) auditObject.get("frms_trans_id"));  //交易单号
        checklist.setCreateTime(transTime); //交易时间（核查单创建时间）
        checklist.setUpdateTime(transTime); //交易时间（核查单更新时间）
        checklist.setPayUserId((String) auditObject.get("frms_user_id")); //支付用户号
        checklist.setBizCode((String) auditObject.get("frms_biz_code")); //业务类型代码
        checklist.setPayBankCardNo((String) auditObject.get("frms_bank_card_no")); //银行卡号
        checklist.setRecTerminalId((String) auditObject.get("frms_machine_id")); //终端编号
        checklist.setRecUserId((String) auditObject.get("frms_merchant_id")); //商户号
        checklist.setRedundantFields1((String) auditObject.get("frms_user_login")); //支付方用户姓名存到冗余字段1中
        checklist.setScore(auditResult.getScore());
        checklist.setRiskLevel(riskLevelMapper.getRiskLevel(auditResult.getScore()));
        checklist.setStatus(0);  // 受理状态 默认待处理

        checklist.setVerifiStrategy(auditResult.getVerifyPolicy().getName()); //验证策略
        checklist.setNotifyStrategy(auditResult.getNotifyPolicy().getName()); //通知策略
        
        /*******************************丹东银行业务开始****************************************/
        setFrmsCheckList(checklist, auditObject, auditResult);
        /*******************************丹东银行业务结束****************************************/
        String resourcesCode = "";
        Integer checklistType = checklist.getChecklistType();
        if(checklistType == null) {
        	// 默认柜台业务
        	checklistType = 1;
        }
        
        switch (checklistType) {
        case 1:
        	// 柜台业务
        	resourcesCode = "1010";
        	break;
        case 2:
        	// 银行卡业务
        	resourcesCode = "1020";
        	break;
        case 3:
        	// 公务卡业务
        	resourcesCode = "1030";
        	break;
        default:
        	logger.error("unknow checklistType{}", checklistType);
        	break;
        }
        // 从拥有核查权限的用户中随机选择一名作为受理人员
        List<Long> userIds = resourcesMapper.selectUserByResourceCode(resourcesCode);
        if (userIds != null && !userIds.isEmpty()) {
            checklist.setOperUserId(userIds.get((int) (Math.random() * userIds.size())));
        } else {
            logger.warn("Couldn't find any user who have the check permission, checklist will be assigned to 'admin'");
            checklist.setOperUserId(1L);   //默认受理人员（处理人员）
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
    public void saveChecklistHis(AuditObject auditObject, AuditResult auditResult, long checklistId, SqlSession sqlSession) {
        if (auditResult.getRisks() == null) {
            return;
        }
        ChecklistHisMapper checklistHisMapper = sqlSession.getMapper(ChecklistHisMapper.class);
        for (Risk risk : auditResult.getRisks()) {
            ChecklistHis checklistHis = new ChecklistHis();
            checklistHis.setChecklistId(checklistId);
            checklistHis.setPrimaryId(auditObject.get("frms_user_id") == null ? "" : auditObject.get("frms_user_id").toString());
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
    
    /**
     * 设置探头数据
     * 
     * @param checklist
     * @param auditObject
     */
    private void setFrmsCheckList(Checklist checklist, AuditObject auditObject, AuditResult auditResult) {
    	 // 说明数据来源
        String frmsDataType = auditObject.get("frms_data_type") == null ? "" : auditObject.get("frms_data_type").toString();
        // 交易时间
        Date frmsTransTime = auditObject.get("frms_trans_time") == null ? null : new Date(Long.valueOf(auditObject.get("frms_trans_time").toString()));
        // 交易金额
        Long frmsTransVol = auditObject.get("frms_trans_vol") == null ? null : Long.valueOf(auditObject.get("frms_trans_vol").toString());
        // 0 正常 1 被冲账 2 冲账 3 被抹账 4 抹账 5 挂帐
        String frmsOperStatus = auditObject.get("frms_oper_status") == null ? "" : auditObject.get("frms_oper_status").toString();
        // 交易渠道
        String frmsOrderChnl = auditObject.get("frms_order_chnl") == null ? "" : auditObject.get("frms_order_chnl").toString();
        // 0-收 1-付  对应借贷标志
        String frmsOrderType = auditObject.get("frms_order_type") == null ? "" : auditObject.get("frms_order_type").toString();
        // 开销户标志0 全部 1 开户 2 销户
        String frmsTradeMode = auditObject.get("frms_trade_mode") == null ? "" : auditObject.get("frms_trade_mode").toString();
        // 现转标志 0-现金 1-转账
        String frmsTradeType = auditObject.get("frms_trade_type") == null ? "" : auditObject.get("frms_trade_type").toString();
        // 客户号
        String frmsUserId = auditObject.get("frms_user_id") == null ? "" : auditObject.get("frms_user_id").toString();
        // 用户证件号或者对公账户户名
        String frmsUserIdCard = auditObject.get("frms_user_id_card") == null ? "" : auditObject.get("frms_user_id_card").toString();
        // 1-对公账户 2-个人账户
        String frmsUserType = auditObject.get("frms_user_type") == null ? "" : auditObject.get("frms_user_type").toString();
        // 银行卡号
        String frmsBankCardNo = auditObject.get("frms_bank_card_no") == null ? "" : auditObject.get("frms_bank_card_no").toString();
        // 转账时才有内容，收款方姓名
        String frmsColName = auditObject.get("frms_col_name") == null ? "" : auditObject.get("frms_col_name").toString();
        // 转账时才有内容，收款方银行卡号
        String frmsColCardNo = auditObject.get("frms_col_card_no") == null ? "" : auditObject.get("frms_col_card_no").toString();
        // 交易柜员
        String frmsOperNo = auditObject.get("frms_oper_no") == null ? "" : auditObject.get("frms_oper_no").toString();
        // 客户姓名
        String frmsUserName = auditObject.get("frms_user_name") == null ? "" : auditObject.get("frms_user_name").toString();
        // 摘要描述
        String frmsPayDetail = auditObject.get("frms_pay_detail") == null ? "" : auditObject.get("frms_pay_detail").toString();
        // 冻结来源  1 冻结 2 控制 3 其他
        String frmsTradeFrom = auditObject.get("frms_trade_from") == null ? "" : auditObject.get("frms_trade_from").toString();
        // 执法部门  1 人民法院 2 税务机关 3 海关 4 人民检察院 5 公安机关 6 国家安全机关 7 监狱 8 走私犯罪侦察机关 9 证券监督管理机关 10 军队保卫部门 11 中国人民银行
        String frmsTradeDepart = auditObject.get("frms_trade_depart") == null ? "" : auditObject.get("frms_trade_depart").toString();
        // 客户账号
        String frmsColCustAcct = auditObject.get("frms_col_cust_acct") == null ? "" : auditObject.get("frms_col_cust_acct").toString();
        // 冻结编号
        String frmsFreId = auditObject.get("frms_fre_id") == null ? "" : auditObject.get("frms_fre_id").toString();
        // 交易流水号
        String frmsSerialsNo = auditObject.get("frms_serials_no") == null ? "" : auditObject.get("frms_serials_no").toString();
        // 帐户类型（借记或贷记卡）
        String frmsBankCardType = auditObject.get("frms_bank_card_type") == null ? "" : auditObject.get("frms_bank_card_type").toString();
        // 交易币种
        String frmsCryType = auditObject.get("frms_cry_type") == null ? "" : auditObject.get("frms_cry_type").toString();
        // 交易金额符号
        String frmsBillFlay = auditObject.get("frms_bill_flay") == null ? "" : auditObject.get("frms_bill_flay").toString();
        // 交易来源
        String frmsTradeSrc = auditObject.get("frms_trade_src") == null ? "" : auditObject.get("frms_trade_src").toString();
        // 商户序号
        String frmsMerchSeq = auditObject.get("frms_merch_seq") == null ? "" : auditObject.get("frms_merch_seq").toString();
        // 商户所在省市
        String frmsMerchStat = auditObject.get("frms_merch_stat") == null ? "" : auditObject.get("frms_merch_stat").toString();
        // 银承协议编号
        String frmsCdhpBh = auditObject.get("frms_cdhp_bh") == null ? "" : auditObject.get("frms_cdhp_bh").toString();
        // 到期日期
        String frmsDueDate = auditObject.get("frms_due_date") == null ? "" : auditObject.get("frms_due_date").toString();
        // 票据状态
        String frmsTransStatus = auditObject.get("frms_trans_status") == null ? "" : auditObject.get("frms_trans_status").toString();
        // 承兑汇票状态
        String frmsCdhpStatus = auditObject.get("frms_cdhp_status") == null ? "" : auditObject.get("frms_cdhp_status").toString();
        // 票面金额
        String frmsCdhpAmt = auditObject.get("frms_cdhp_amt") == null ? "" : auditObject.get("frms_cdhp_amt").toString();
        // 对公账户户名
        String frmsCompanyName = auditObject.get("frms_company_name") == null ? "" : auditObject.get("frms_company_name").toString();
        // 交易柜员姓名
        String frmsOperName = auditObject.get("frms_oper_name") == null ? "" : auditObject.get("frms_oper_name").toString();
        // 网银签约时间 "20150628"
        String frmsSignDateNet = auditObject.get("frms_sign_date_net") == null ? "" : auditObject.get("frms_sign_date_net").toString();
        // 手机银行签约时间 "20150628"
        String frmsSignDateMob = auditObject.get("frms_sign_date_mob") == null ? "" : auditObject.get("frms_sign_date_mob").toString();
        // 手机银行签约的手机号码
        String frmsSignMobNum = auditObject.get("frms_sign_mob_num") == null ? "" : auditObject.get("frms_sign_mob_num").toString();
        // 商户名字
        String frmsMerchName = auditObject.get("frms_merch_name") == null ? "" : auditObject.get("frms_merch_name").toString();
        // 客户姓名
        String frmsCustName = auditObject.get("frms_cust_name") == null ? "" : auditObject.get("frms_cust_name").toString();
        // 电话号码
        String frmsUserPhone = auditObject.get("frms_user_phone") == null ? "" : auditObject.get("frms_user_phone").toString();
        // 核查单类型  1. 柜台业务，2. 银行卡业务（包括网银、 手机银行），3. 公务卡业务
        Integer checklistType = getCheckListType(frmsDataType, frmsOrderChnl);
        
        String rulesCode = "";
        // 保存规则代码
        for (Risk risk : auditResult.getRisks()) {
        	if (risk.getRuleName() != null) {
                String[] ruleNames = risk.getRuleName().split(":");
                rulesCode += (ruleNames.length > 1 ? ruleNames[1].trim() : "") + ",";
            }
        }
        if(rulesCode != null && !"".equals(rulesCode) && rulesCode.endsWith(",")) {
        	checklist.setRulesCode(rulesCode.substring(0, rulesCode.length() - 1));
        }
        checklist.setFrmsDataType(frmsDataType);
        checklist.setFrmsTransTime(frmsTransTime);
        checklist.setFrmsTransVol(frmsTransVol);
        checklist.setFrmsOperStatus(frmsOperStatus);
        checklist.setFrmsOrderChnl(frmsOrderChnl);
        checklist.setFrmsOrderType(frmsOrderType);
        checklist.setFrmsTradeMode(frmsTradeMode);
        checklist.setFrmsTradeType(frmsTradeType);
        checklist.setFrmsUserId(frmsUserId);
        checklist.setFrmsUserIdCard(frmsUserIdCard);
        checklist.setFrmsUserType(frmsUserType);
        checklist.setFrmsBankCardNo(frmsBankCardNo);
        checklist.setFrmsColName(frmsColName);
        checklist.setFrmsColCardNo(frmsColCardNo);
        checklist.setFrmsOperNo(frmsOperNo);
        checklist.setFrmsUserName(frmsUserName);
        checklist.setFrmsPayDetail(frmsPayDetail);
        checklist.setFrmsTradeFrom(frmsTradeFrom);
        checklist.setFrmsTradeDepart(frmsTradeDepart);
        checklist.setFrmsColCustAcct(frmsColCustAcct);
        checklist.setFrmsFreId(frmsFreId);
        checklist.setFrmsSerialsNo(frmsSerialsNo);
        checklist.setFrmsBankCardType(frmsBankCardType);
        checklist.setFrmsCryType(frmsCryType);
        checklist.setFrmsBillFlay(frmsBillFlay);
        checklist.setFrmsTradeSrc(frmsTradeSrc);
        checklist.setFrmsMerchSeq(frmsMerchSeq);
        checklist.setFrmsMerchStat(frmsMerchStat);
        checklist.setFrmsCdhpBh(frmsCdhpBh);
        checklist.setFrmsDueDate(frmsDueDate);
        checklist.setFrmsTransStatus(frmsTransStatus);
        checklist.setFrmsCdhpStatus(frmsCdhpStatus);
        checklist.setFrmsCdhpAmt(frmsCdhpAmt);
        checklist.setFrmsCompanyName(frmsCompanyName);
        checklist.setFrmsOperName(frmsOperName);
        checklist.setFrmsSignDateNet(frmsSignDateNet);
        checklist.setFrmsSignDateMob(frmsSignDateMob);
        checklist.setFrmsSignMobNum(frmsSignMobNum);
        checklist.setFrmsMerchName(frmsMerchName);
        checklist.setChecklistType(checklistType);
        checklist.setFrmsCustName(frmsCustName);
        checklist.setFrmsUserPhone(frmsUserPhone);
    }
    
    /**
     * 根据数据来源和交易渠道获取核查单类型
     * 
     * @param frmsDataType 说明数据来源
     * @param frmsOrderChnl 交易渠道
     * @return
     */
    private Integer getCheckListType(String frmsDataType, String frmsOrderChnl) {
    	if(frmsDataType.equals("azjcph")) {
			if(frmsOrderChnl.equals("00")) {
				// 柜台交易数据
				return 1;
			} else if(frmsOrderChnl.equals("17") || frmsOrderChnl.equals("51") 
					|| frmsOrderChnl.equals("08") || frmsOrderChnl.equals("22")
					|| frmsOrderChnl.equals("15")) {
				// 网银、手机银行银行卡业务核查单
				return 2;
			} else {
				logger.error("unknow frmsOrderChnl{}", frmsOrderChnl);
				return null;
			}
		} else if(frmsDataType.equals("freeze")) {
			// 冻结账户扣划
			return 1;
		} else if(frmsDataType.equals("bcdhp")) {
			// 承兑汇票
			return 1;
		} else if(frmsDataType.equals("event")) {
			return 3;
		} else {
			logger.error("unknow frmsDataType{}", frmsDataType);
			return null;
		}
    }
}