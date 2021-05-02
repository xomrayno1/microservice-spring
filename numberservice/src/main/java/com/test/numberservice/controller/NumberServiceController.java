package com.test.numberservice.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberServiceController {
	
	@RequestMapping
	public String sayHello() {
		return "Hello number service";
	}
	@RequestMapping("/randomNumber")
	public int generateRandomNumber() {
		return ThreadLocalRandom.current().nextInt(1, 10);
	}
}
