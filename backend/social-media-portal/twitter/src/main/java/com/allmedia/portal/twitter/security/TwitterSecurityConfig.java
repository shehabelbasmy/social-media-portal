package com.allmedia.portal.twitter.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;

import com.allmedia.portal.twitter.service.TwitterUserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class TwitterSecurityConfig {

	private final TwitterAuthSuccessHandler authSuccessHandler;
	private final TwitterUserService twitterUserService;
	@Bean
	public SecurityFilterChain securityFilterChainTwitter(HttpSecurity http,
			OAuth2AuthorizationRequestResolver resolver) throws Exception {
		return http.requestMatchers().antMatchers("/pkce/oauth2/authorization/twitter","/login/oauth2/code/twitter")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.authorizationEndpoint(a->a.authorizationRequestResolver(resolver))
				.successHandler(authSuccessHandler)
				.authorizedClientService(twitterUserService))
			.build();
	}
	
	@Bean
    public OAuth2AuthorizationRequestResolver pkceResolver(ClientRegistrationRepository repo) {
        DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/pkce/oauth2/authorization");
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return resolver;
    }
}
