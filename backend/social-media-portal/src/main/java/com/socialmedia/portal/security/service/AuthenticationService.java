package com.socialmedia.portal.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.socialmedia.portal.security.request.AuthenticationRequest;
import com.socialmedia.portal.security.request.TokenDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	public TokenDto authenticate(AuthenticationRequest authRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = 
			new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
		authenticationToken=(UsernamePasswordAuthenticationToken)authenticationManager.authenticate(authenticationToken);
		return tokenService.generateToken(authenticationToken);
	}
	
	
}
