package com.test.configclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.test.configclient.config.ClientConfig;

@RestController
@RequestScope
public class ConfigClientController {
	@Autowired
	private ClientConfig clientConfig;
	
	@Value("${sample.property2}") // @value thông tin cấu hình k đc tự động refresh
	private String property2; //nếu muốn nó tự đông refresh thì phải thêm @RequestScope
	

	@RequestMapping("/config")
	public String printConfig() {
		
		return clientConfig.getProperty1() + " || "+ property2;
	}
	// tư động refresh cấu hình thông qua http://localhost:8080/actuator/refresh POST
	// cơ chế refresh của configserver
}
