package com.test.demoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoserviceApplication.class, args);
	}

}
