package cn.com.bsfit.frms.policy.cfg;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import javax.naming.NamingException;
import java.util.Properties;

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

    @Bean
    public JndiTemplate jndiTemplate() {
        Properties prop = new Properties();
        prop.put("java.naming.factory.initial", initial);
        prop.put("java.naming.provider.url", url);
        prop.put("java.naming.factory.url.pkgs", pkgs);
        return new JndiTemplate(prop);
    }

    @Bean
    public ConnectionFactory connectionFactory(JndiTemplate jndiTemplate) throws IllegalArgumentException, NamingException {
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        bean.setJndiName(name);
        bean.setJndiTemplate(jndiTemplate);
        bean.afterPropertiesSet();
        return (ConnectionFactory) bean.getObject();
    }

    @Bean
    public CachingConnectionFactory cachedConnectionFactory(@Qualifier("connectionFactory") ConnectionFactory factory) {
        CachingConnectionFactory cFactory = new CachingConnectionFactory();
        cFactory.setTargetConnectionFactory(factory);
        cFactory.setSessionCacheSize(50);
        cFactory.setReconnectOnException(true);
        return cFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(@Qualifier("cachedConnectionFactory") CachingConnectionFactory cFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(cFactory);
        jmsTemplate.setDefaultDestinationName("FrmsRiskArchiveQueue");
        return jmsTemplate;
    }
}

