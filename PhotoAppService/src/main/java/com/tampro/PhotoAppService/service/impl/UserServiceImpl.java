package com.tampro.PhotoAppService.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tampro.PhotoAppService.dto.UserDto;
import com.tampro.PhotoAppService.entity.Users;
import com.tampro.PhotoAppService.repository.UserRepository;
import com.tampro.PhotoAppService.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepo;

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepo = userRepo;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserDto createUser(UserDto userDetails) {
		// TODO Auto-generated method stub
		userDetails.setUserId(UUID.randomUUID().toString());
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		Users users = modelMapper.map(userDetails, Users.class);
		users.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

		Users createdUsers = userRepo.save(users);

		UserDto userDto = modelMapper.map(createdUsers, UserDto.class);

		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users users = userRepo.findByEmail(username);
		if (users == null)	throw new UsernameNotFoundException(username);
		return new User(users.getEmail(), users.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		Users users = userRepo.findByEmail(email);
		if (users == null)	throw new UsernameNotFoundException(email);
		return new ModelMapper().map(users, UserDto.class);
	}

}
