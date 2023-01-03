package com.allmedia.portal.facebook.contoller;

import java.util.Set;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.allmedia.portal.security.service.CustomOAuth2AuthorizedClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/facebook")
@AllArgsConstructor
public class FacebookController {

	private final CustomOAuth2AuthorizedClientService auth2AuthorizedClientService;
	@GetMapping("/user/token")
	public String getUserToken(
			@RegisteredOAuth2AuthorizedClient("facebook") OAuth2AuthorizedClient authentication) {
		return authentication.getAccessToken().getTokenValue();
	}
	@GetMapping("/page/token")
	public String getPageToken(@CurrentSecurityContext(expression = "authentication") JwtAuthenticationToken authenticationToken) {
		Set<MyOAuth2AuthorizedClient> findByUserEmail = auth2AuthorizedClientService.findByUserEmail(authenticationToken.getName());
		return new String(findByUserEmail.iterator().next().getFacebookPageToken().iterator().next().getToken());
	}
}
