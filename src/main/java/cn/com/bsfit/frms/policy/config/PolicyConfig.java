package cn.com.bsfit.frms.policy.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池配置
 * 
 * @author hjp
 * 
 * @since 1.0.0
 *
 */
@Configuration
public class PolicyConfig {
	
	@Value("${policy.threadsize:4}")
    private int policyThreadSize;
	
	@Bean
	public ThreadPoolExecutor policyThreadPool() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(policyThreadSize);
		return executor;
	}
}
