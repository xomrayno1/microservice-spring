package com.test.demoservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoServiceController {
	
	@Value("${server.port}")
	private String port;
	
	@GetMapping
	public String printInfo() {
		return "demo service. Port: "+ port;
	}

}
