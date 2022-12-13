package com.socialmedia.portal.contoller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/token")
	public String get(
		@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient clientid) {
		
		return clientid.getAccessToken().getTokenValue();
	}
	
}
