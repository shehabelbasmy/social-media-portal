package com.allmedia.portal.facebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.service.FacebookUserService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(BaseController.BASE_URI+"/facebook/auth")
@AllArgsConstructor
public class FacebookAuthController{

	private final FacebookUserService  facebookUserService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> register(@PathVariable("userId")String userId){
		facebookUserService.register(userId);
		return ResponseEntity.ok().build();
	}
}
