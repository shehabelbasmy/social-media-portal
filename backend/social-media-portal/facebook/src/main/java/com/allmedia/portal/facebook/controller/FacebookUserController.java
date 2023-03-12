package com.allmedia.portal.facebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.framework.controller.BaseController;

@RestController
@RequestMapping(BaseController.BASE_URI+"/facebbok/user")
public class FacebookUserController {

	@GetMapping("/posts")
	public ResponseEntity<?> getUserPosts(){
		return null;
	}
}
