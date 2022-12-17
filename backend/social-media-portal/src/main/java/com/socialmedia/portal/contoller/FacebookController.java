package com.socialmedia.portal.contoller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/social/facebook")
@AllArgsConstructor
public class FacebookController {

//	private final OAuth2AuthorizedClientService authorizedClientService;
//	private final OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;

	@GetMapping("/page/post")
	public String addPost(
		@RegisteredOAuth2AuthorizedClient("linkedin") OAuth2AuthorizedClient authentication) {
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
