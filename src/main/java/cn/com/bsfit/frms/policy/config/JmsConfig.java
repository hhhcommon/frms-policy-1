package cn.com.bsfit.frms.policy.config;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import cn.com.bsfit.frms.policy.jms.ChecklistListener;
import cn.com.bsfit.frms.policy.utils.BatchMessageListenerContainer;

@Configuration
public class JmsConfig {

    @Value("${frms.rams.jms.initial:org.jnp.interfaces.NamingContextFactory}")
    private String initial;
    @Value("${frms.rams.jms.url:jnp://10.100.1.53:1099}")
    private String url;
    @Value("${frms.rams.jms.pkgs:org.jboss.naming:org.jnp.interfaces}")
    private String pkgs;
    @Value("${frms.rams.jms.name:/ConnectionFactory}")
    private String name;
    @Value("${destinationName:FrmsRiskArchiveQueue}")
    private String destinationName;
    
    @Bean
    public JndiTemplate jndiTemplate() {
        Properties prop = new Properties();
        prop.put("java.naming.factory.initial", initial);
        prop.put("java.naming.provider.url", url);
        prop.put("java.naming.factory.url.pkgs", pkgs);
        return new JndiTemplate(prop);
    }

    @Autowired
    private JndiTemplate jndiTemplate;
    
    @Bean
    public ConnectionFactory connectionFactory() throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(name);
        bean.setJndiTemplate(jndiTemplate);
        bean.afterPropertiesSet();
        return (ConnectionFactory) bean.getObject();
    }

    @Autowired
    @Qualifier(value = "connectionFactory")
    private ConnectionFactory connectionFactory;
    
    @Bean
    public CachingConnectionFactory cachedConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory);
        cachingConnectionFactory.setSessionCacheSize(50);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    
    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
        jmsTemplate.setDefaultDestinationName(destinationName);
        return jmsTemplate;
    }
    
    @Bean
	public BatchMessageListenerContainer batchMessageListener(ChecklistListener checklistListener) {
		BatchMessageListenerContainer container = new BatchMessageListenerContainer();
		container.setConnectionFactory(cachingConnectionFactory);
		container.setDestinationName(destinationName);
		container.setMessageListener(checklistListener);
		return container;
	}
}

