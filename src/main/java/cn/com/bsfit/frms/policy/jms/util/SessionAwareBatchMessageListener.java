package cn.com.bsfit.frms.policy.jms.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

/**
 * 主要用于批量消费消息
 * 
 * @author 王新根
 * @since 2.3.0
 *
 * @param <M>
 */
public interface SessionAwareBatchMessageListener<M extends Message> {
    /**
     * Perform a batch action with the provided list of {@code messages}.
     * 
     * @param session
     *            JMS {@code Session} that received the messages
     * @param messages
     *            List of messages to be processed as a unit of work
     * @throws javax.jms.JMSException
     *             JMSException thrown if there is an error performing the
     *             operation.
     */
    public void onMessages(Session session, List<M> messages) throws JMSException;
}