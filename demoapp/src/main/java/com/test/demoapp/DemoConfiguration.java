package com.test.demoapp;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class DemoConfiguration {

	@Bean
	@LoadBalanced
	// rest template này khi được sử dụng để gọi đến 1 cái service nào đó
	// thì nó luôn chia tải đều cho các instance trong service đó
	// no chia tải lần lượt
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	// interface quản lý các thuật toán chia tải instance
	@Bean
	public IRule getRule() {
		return new RandomRule();
		// overidde lại thuật toán loadbalanced để nó chia theo random
		// mặc định chia theo thứ tự
	}
}
