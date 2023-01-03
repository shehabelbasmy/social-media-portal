package com.allmedia.portal.twitter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.framework.controller.BaseController;
import com.allmedia.portal.twitter.service.TwitterUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(BaseController.BASE_URI+"/twitter/auth")
@AllArgsConstructor
public class TwitterAuthController {

	private final TwitterUserService twitterUserService;
	@GetMapping("/{userId}")
	public ResponseEntity<?>register(@PathVariable("userId")String userId){
		twitterUserService.register(userId);
		return ResponseEntity.ok().build();
	}
}
