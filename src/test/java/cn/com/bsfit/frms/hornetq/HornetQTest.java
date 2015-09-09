package cn.com.bsfit.frms.hornetq;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.core.client.impl.DelegatingSession;
import org.hornetq.jms.client.HornetQConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.com.bsfit.frms.policy.CheckListTest;

public class HornetQTest {

	private static final String HORNETQ_PROVIDER_URL = "jnp://127.0.0.1:10099";
	private static final String INITIAL_CONTEXT_FACTORY = "org.jnp.interfaces.NamingContextFactory";
	private static final String FACTORY_URL_PKGS = "org.jboss.naming:org.jnp.interfaces";
	
	public static void main(String[] args) throws NamingException, JMSException, SQLException {
		ResultSet re = CheckListTest.getChecklistResultSet();
		final String text = JSON.toJSONString(re,
				SerializerFeature.BrowserCompatible,
				SerializerFeature.WriteClassName,
				SerializerFeature.DisableCircularReferenceDetect);
		Connection initConnection = createConnectionWithJNDI();
		Queue queue = createQueueWithJNDI();
		System.out.println(getServer(initConnection));
		Session session = initConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		MessageProducer producer = session.createProducer(queue);
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		producer.send(session.createTextMessage(text));
		// producer.send(session.createTextMessage("Hello"));
		releaseConnection(initConnection);
	}

	/**
	 * 关闭连接
	 * 
	 * @param connection
	 * @throws JMSException
	 */
	private static void releaseConnection(Connection connection) throws JMSException {
		if (connection != null) {
			connection.close();
		}
	}

	public static Connection createConnectionWithJNDI() throws NamingException, JMSException {
		Properties properties = new Properties();
		properties.put("java.naming.provider.url", HORNETQ_PROVIDER_URL);
		properties.put("java.naming.factory.initial", INITIAL_CONTEXT_FACTORY);
		properties.put("java.naming.factory.url.pkgs", FACTORY_URL_PKGS);
		InitialContext initialContext = new InitialContext(properties);
		ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("/ConnectionFactory");
		Connection connection = connectionFactory.createConnection();
		return connection;
	}

	public static Queue createQueueWithJNDI() throws NamingException {
		Properties properties = new Properties();
		properties.put("java.naming.provider.url", HORNETQ_PROVIDER_URL);
		properties.put("java.naming.factory.initial", INITIAL_CONTEXT_FACTORY);
		properties.put("java.naming.factory.url.pkgs", FACTORY_URL_PKGS);
		InitialContext initialContext = new InitialContext(properties);
		Queue queue = (Queue) initialContext.lookup("/queue/ExpiryQueue");
		return queue;
	}

	private static int getServer(Connection connection) {
		DelegatingSession session = (DelegatingSession) ((HornetQConnection) connection).getInitialSession();

		TransportConfiguration transportConfiguration = session.getSessionFactory().getConnectorConfiguration();
		String port = transportConfiguration.getParams().get("port").toString();
		return Integer.valueOf(port);
	}
}
