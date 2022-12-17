package com.socialmedia.portal.security.config;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CustomOAuth2AuthorizationSuccessHandler implements OAuth2AuthorizationSuccessHandler {

	private final ClientRegistrationRepository clientRegistrationRepository;
	
	@Override
	public void onAuthorizationSuccess(OAuth2AuthorizedClient authorizedClient, 
			Authentication principal,
			Map<String, Object> attributes) {
		ClientRegistration findByRegistrationId = clientRegistrationRepository.findByRegistrationId("facebook");
		
		System.err.println(findByRegistrationId);
	}

}
