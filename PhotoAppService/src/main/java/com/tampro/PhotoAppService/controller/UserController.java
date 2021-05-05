package com.tampro.PhotoAppService.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tampro.PhotoAppService.dto.UserDto;
import com.tampro.PhotoAppService.request.model.CreateUserRequestModel;
import com.tampro.PhotoAppService.response.model.CreateUserResponseModel;
import com.tampro.PhotoAppService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private Environment env;
	

	@GetMapping("/status/check")
	public String status() {
		return "Working..."+env.getProperty("spring.instance.name")
						+" Port : "+env.getProperty("server.port")
						+" Spring instance from github : "+env.getProperty("spring.instance")
						+" , with token = "+env.getProperty("token.secret");
	}
	@Autowired
	UserService userService;
	
	@PostMapping
	public ResponseEntity<CreateUserResponseModel> create(@Valid @RequestBody CreateUserRequestModel requestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userDto = modelMapper.map(requestModel, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);
		
		CreateUserResponseModel createUserResponseModel = modelMapper.map(createdUser, CreateUserResponseModel.class);
		
		return new ResponseEntity(createUserResponseModel,HttpStatus.CREATED);
	}
}
