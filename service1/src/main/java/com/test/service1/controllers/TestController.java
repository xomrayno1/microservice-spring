package com.test.service1.controllers;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping
	public String sayHello() {
		return "hello from service 1.";
	}
	
	@RequestMapping("/notifi")
	public String notifi(@RequestHeader("requestId") String requestId) {
		return "Service 1 notifi. Request ID "+ requestId;
	}
}
