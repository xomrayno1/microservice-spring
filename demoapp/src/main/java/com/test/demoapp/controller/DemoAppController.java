package com.test.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoAppController {
	@Autowired
	private RestTemplate restTemplate;
	

	@GetMapping
	public String getInfo() {	 
		return this.restTemplate.getForEntity("http://demoservice", String.class).getBody();
	}
}
