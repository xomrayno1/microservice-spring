package com.test.configclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "sample") // tự động lấy cấu hình
public class ClientConfig {
	private String property1; //sample.property1 trên github

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}
	

}
