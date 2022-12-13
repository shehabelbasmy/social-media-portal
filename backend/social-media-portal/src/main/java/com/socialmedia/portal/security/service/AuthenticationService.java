package com.socialmedia.portal.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.socialmedia.portal.security.request.AuthenticationRequest;
import com.socialmedia.portal.security.request.TokenDto;
import com.socialmedia.portal.service.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	private final UserDetailsService userService;
	
	public TokenDto authenticate(AuthenticationRequest authRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = 
			new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
		authenticationToken=(UsernamePasswordAuthenticationToken)authenticationManager.authenticate(authenticationToken);
		return tokenService.generateToken(authenticationToken);
	}
	
	
	public void updateUser(String email,String userId,String registrationId) {
		((UserDetailsServiceImpl)userService).updateUser(email,userId,registrationId);
	}
	
}
