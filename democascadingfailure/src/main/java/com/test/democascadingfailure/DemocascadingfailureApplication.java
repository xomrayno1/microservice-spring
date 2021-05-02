package com.test.democascadingfailure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class DemocascadingfailureApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemocascadingfailureApplication.class, args);
	}

}
