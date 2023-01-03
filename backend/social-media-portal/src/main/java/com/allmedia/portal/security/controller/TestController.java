package com.allmedia.portal.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/test")
@RestController
@Secured("SCOPE_SUPER_USER")
public class TestController {
	
	@GetMapping
	public String test() {
		return "test";
	}

}
