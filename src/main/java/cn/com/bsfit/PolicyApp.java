package cn.com.bsfit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.connection.CachingConnectionFactory;

import cn.com.bsfit.frms.policy.jms.ChecklistListener;
import cn.com.bsfit.frms.policy.utils.BatchMessageListenerContainer;

@ComponentScan
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class PolicyApp {
	
	@Bean
	public BatchMessageListenerContainer batchMessageListener(@Qualifier("cachedConnectionFactory") CachingConnectionFactory cFactory, 
			ChecklistListener checklistListener) {
		BatchMessageListenerContainer container = new BatchMessageListenerContainer();
		container.setConnectionFactory(cFactory);
		container.setDestinationName("FrmsRiskArchiveQueue");
		container.setMessageListener(checklistListener);
		return container;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PolicyApp.class, args);
	}
}
