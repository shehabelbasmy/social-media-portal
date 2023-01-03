package com.allmedia.portal.contolller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.framework.controller.BaseController;
import com.allmedia.portal.security.request.AuthenticationRequest;
import com.allmedia.portal.security.response.TokenDto;
import com.allmedia.portal.security.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RequestMapping(BaseController.BASE_URI+ "/auth")
@RestController
@AllArgsConstructor
public class AuthController {

	private final AuthenticationService authService;

	@PostMapping("/login")
	public TokenDto login(@RequestBody @Valid AuthenticationRequest authRequest) {
		return authService.authenticate(authRequest);
	}

}
