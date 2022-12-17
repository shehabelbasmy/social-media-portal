package com.socialmedia.portal.contoller;

import javax.validation.Valid;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialmedia.portal.security.response.dto.AuthenticationRequest;
import com.socialmedia.portal.security.response.dto.TokenDto;
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
	
	@GetMapping("/{registrationId}/{userId}")
	public String test(@PathVariable("userId")String userId,
		@CurrentSecurityContext(expression="authentication") JwtAuthenticationToken authenticationToken,
		@PathVariable("registrationId")String registrationId) {
		String email=authenticationToken.getName();
		authService.updateUser(email, userId, registrationId);
		return userId+authenticationToken.getName();
	}
}
