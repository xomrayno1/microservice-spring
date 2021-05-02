package com.test.service2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping
	public String sayHello() {
		return "Say hello service 2";
	}
	@RequestMapping("/notifi")
	public String notifi() {
		return "Service 2 notifi";
	}
}
