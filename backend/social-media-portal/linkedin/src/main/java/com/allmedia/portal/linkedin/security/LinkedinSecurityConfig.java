package com.allmedia.portal.linkedin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.allmedia.portal.linkedin.service.LinkedinUserService;
import com.allmedia.portal.security.service.GlobalOAuth2UserService;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class LinkedinSecurityConfig {
	
	private final LinkedInSuccessHandler successHandler;
	private final LinkedinUserService clientService;
	private final GlobalOAuth2UserService userService;
	private final LinkedInOAuth2AccessTokenResponseClient accessTokenResponse;
	
	@Bean
	public SecurityFilterChain securityFilterChainLinkedin(HttpSecurity http) throws Exception {
		
		return http.requestMatchers().antMatchers("/oauth2/authorization/linkedin","/login/oauth2/code/linkedin")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.successHandler(successHandler)
				.authorizedClientService(clientService)
				.userInfoEndpoint(info->info.userService(userService))
				.tokenEndpoint(a->a.accessTokenResponseClient(accessTokenResponse)))
			.build();
	}
}
