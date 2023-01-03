package com.allmedia.portal.facebook.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.allmedia.portal.facebook.service.FacebookUserService;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
public class FacebookSecurityConfig {

	private final FacebookAuthenticationSuccessHandler successHandler;
	private final FacebookUserService facebookUserService;
	@Bean
	public SecurityFilterChain securityFilterChainFacebook(HttpSecurity http) throws Exception {
		return http.requestMatchers().antMatchers("/oauth2/authorization/facebook","/login/oauth2/code/facebook")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.successHandler(successHandler)
				.authorizedClientService(facebookUserService))
			.build();
	}
}
