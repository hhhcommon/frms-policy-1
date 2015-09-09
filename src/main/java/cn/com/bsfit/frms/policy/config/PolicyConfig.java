package cn.com.bsfit.frms.policy.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PolicyConfig {

	@Value("${policyThreadSize:4}")
	private int policyThreadSize;

	@Bean(destroyMethod = "shutdown", name = "policyThreadPool")
	public ThreadPoolExecutor policyThreadPool() {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(policyThreadSize);
		return executor;
	}
}
