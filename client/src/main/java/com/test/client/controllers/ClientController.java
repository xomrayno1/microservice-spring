package com.test.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ClientController {
	
	@Autowired
	private EurekaClient eurekaClient;
	
	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	@RequestMapping("/")
	public String callService() {
		//lấy về instance của service
		// gọi lên server truyền tên service muốn gọi
		InstanceInfo instanceInfo =	eurekaClient.getNextServerFromEureka("service", false); //truyền vào tên service muốn gọi
		String url = instanceInfo.getHomePageUrl();
		RestTemplate restTemplate =	restTemplateBuilder.build();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class); // lấy về kiểu String
		return response.getBody();
	}

}
