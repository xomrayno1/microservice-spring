package com.test.gatewayservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.gatewayservice.filters.AddRequestIdFilter;

@Configuration
public class GatewayServiceConfiguration {

	@Bean // filter service
	public AddRequestIdFilter addRequestIdFilter() {
		return new AddRequestIdFilter();
	}
}
