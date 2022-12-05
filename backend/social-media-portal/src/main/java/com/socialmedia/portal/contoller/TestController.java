package com.socialmedia.portal.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/oauth")
public class TestController {
	
	@GetMapping("/get")
	public String get() {
		return "get";
	}

}
