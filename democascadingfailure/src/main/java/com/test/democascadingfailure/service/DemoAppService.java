package com.test.democascadingfailure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class DemoAppService {
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "zeroNumber") // chỉ hoạt động ở @Service và @Component
	public int getRandomNumber() {
		return this.restTemplate.getForEntity("http://numberservice/randomNumber", Integer.class).getBody();
	}
	public int zeroNumber() {
		return 0;
	}

}
