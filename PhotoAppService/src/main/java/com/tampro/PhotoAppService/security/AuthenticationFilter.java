package com.tampro.PhotoAppService.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tampro.PhotoAppService.dto.UserDto;
import com.tampro.PhotoAppService.response.model.LoginRequestModel;
import com.tampro.PhotoAppService.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private UserService userService;
	private Environment env;
	
	
	
	 
	public AuthenticationFilter(UserService userService, Environment env, AuthenticationManager authenticationManager) {
		this.userService = userService;
		this.env = env;
		super.setAuthenticationManager(authenticationManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, 
			HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
			LoginRequestModel requestModel =  new ObjectMapper().
								readValue(request.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(
						requestModel.getEmail()
						,requestModel.getPassword()
						,new ArrayList<>()
						));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDto userDto = userService.getUserDetailsByEmail(userName);
		String token = Jwts.builder()
				.setSubject(userDto.getUserId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512,  env.getProperty("token.secret"))
				.compact();
		response.addHeader("token", token);
		response.addHeader("userId", userDto.getUserId());
	}
}
