package com.test.democascadingfailure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.democascadingfailure.service.DemoAppService;

@RestController
public class DemoAppController {
	@Autowired
	private DemoAppService demoAppService;
	
	@GetMapping("/printNumber")
	public String printNumber() {
		return "The random number is : "+ demoAppService.getRandomNumber();
	}
}
