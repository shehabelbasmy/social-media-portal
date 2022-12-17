package com.socialmedia.portal.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizationSuccessHandler;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class OAuthClientConfig{
	
	private final OAuth2AuthorizationSuccessHandler handler;
//	private final OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;

//	@Bean
//	public OAuth2AuthorizedClientService oAuth2AuthorizedClientService
//	        (JdbcOperations jdbcOperations, ClientRegistrationRepository clientRegistrationRepository) {
//	    return new JdbcOAuth2AuthorizedClientService(jdbcOperations,clientRegistrationRepository);
//	}
	
	@Bean
	public OAuth2AuthorizedClientManager authorizedClientManager(
			ClientRegistrationRepository clientRegistrationRepository,
			OAuth2AuthorizedClientRepository authorizedClientRepository) {

		OAuth2AuthorizedClientProvider authorizedClientProvider =
			OAuth2AuthorizedClientProviderBuilder.builder()
				.authorizationCode()
				.refreshToken()
				.clientCredentials()
				.password()
				.build();
		DefaultOAuth2AuthorizedClientManager authorizedClientManager =
			new DefaultOAuth2AuthorizedClientManager(
				clientRegistrationRepository,authorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
		authorizedClientManager.setAuthorizationSuccessHandler(handler);
		return authorizedClientManager;
	}
	
}
