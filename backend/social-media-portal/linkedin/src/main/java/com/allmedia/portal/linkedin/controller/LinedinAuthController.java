package com.allmedia.portal.linkedin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.framework.controller.BaseController;
import com.allmedia.portal.linkedin.service.LinkedinUserService;

import lombok.AllArgsConstructor;

@RequestMapping(BaseController.BASE_URI+"/linkedin/auth")
@RestController
@AllArgsConstructor
public class LinedinAuthController {

	private final LinkedinUserService linkedInUserService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> register(@PathVariable("userId")String userId){
		linkedInUserService.register(userId);
		return ResponseEntity.accepted().build();
	}
}
