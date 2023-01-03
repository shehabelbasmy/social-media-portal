package com.allmedia.portal.facebook.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/facebbok/user")
public class FacebookUserController {

	@GetMapping("/posts")
	public ResponseEntity<?> getUserPosts(){
		return null;
	}
}
