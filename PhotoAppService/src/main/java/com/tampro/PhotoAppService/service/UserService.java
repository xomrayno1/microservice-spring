package com.tampro.PhotoAppService.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.tampro.PhotoAppService.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto userDetails);
	UserDto getUserDetailsByEmail(String email);
}
