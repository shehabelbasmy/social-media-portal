package com.socialmedia.portal.contoller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.portal.security.request.AuthenticationRequest;
import com.socialmedia.portal.security.request.TokenDto;
import com.socialmedia.portal.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final AuthenticationService authService;
	
	@PostMapping("/login")
	public TokenDto login(@RequestBody @Valid AuthenticationRequest authRequest) {
		return authService.authenticate(authRequest);
	}
}
