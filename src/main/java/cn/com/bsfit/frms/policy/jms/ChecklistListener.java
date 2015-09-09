package cn.com.bsfit.frms.policy.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import cn.com.bsfit.frms.policy.mapper.portal.ResourcesMapper;
import cn.com.bsfit.frms.policy.mapper.portal.RiskLevelMapper;
import cn.com.bsfit.frms.policy.utils.BSfitUtils;
import cn.com.bsfit.frms.policy.utils.SessionAwareBatchMessageListener;

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

	@Autowired
	@Qualifier("policyThreadPool")
	private ThreadPoolExecutor executor;

	public void onMessages(Session session, List<TextMessage> messages) throws JMSException {
		List<List<TextMessage>> subList = BSfitUtils.partition(messages, executor.getCorePoolSize());
		List<ChecklistCallable> checklistCallableList = new ArrayList<ChecklistCallable>();
		for (List<TextMessage> msg : subList) {
			checklistCallableList.add(new ChecklistCallable(session, ramsJdbcType, 
					riskLevelMapper, resourcesMapper, sqlSessionFactory, msg));
		}
		try {
			executor.invokeAll(checklistCallableList);
		} catch (InterruptedException e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}