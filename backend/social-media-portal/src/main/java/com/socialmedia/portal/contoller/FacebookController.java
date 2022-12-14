package com.socialmedia.portal.contoller;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/social/facebook")
@AllArgsConstructor
public class FacebookController {

//	private final OAuth2AuthorizedClientService authorizedClientService;
//	private final OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;

	@GetMapping("/page/post")
	public String addPost(
		@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authentication) {
//		String userId= authentication.getPrincipalName();
//		String token=authentication.getAccessToken().getTokenValue();
//		String url="https://graph.facebook.com/"+userId+"/accounts";
//		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url)
//            .queryParam("access_token", token);
//		HttpHeaders headers = new HttpHeaders() {{
//	        setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//	        setAccept(List.of(MediaType.APPLICATION_JSON));
//	    }};
//	    String uriString = uriBuilder.toUriString();
//		ResponseEntity<String> request = new RestTemplate().exchange(uriString, 
//                HttpMethod.GET, new HttpEntity<>(headers), String.class);
		return authentication.getAccessToken().getTokenValue();
//		return null;
	}
}
