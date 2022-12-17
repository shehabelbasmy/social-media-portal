package com.socialmedia.portal.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestCustomizers;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.socialmedia.portal.security.service.CustomDefaultOAuth2UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsDervice;
	private final RsaKeyProperties jwtConfigProperties;
	private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
	private final AuthenticationSuccessHandler successHandler;
	private final CustomDefaultOAuth2UserService userService;
	private final OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponse;
	
	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE)
	public SecurityFilterChain securityFilterChainApi(HttpSecurity http,
			OAuth2AuthorizationRequestResolver resolver) throws Exception {
		return http.requestMatchers().antMatchers("/api/v1/**")
			.and()
			.csrf().disable()
			.authorizeRequests(
					auth -> auth.antMatchers("/api/v1/auth/**").permitAll()
					.anyRequest().authenticated())
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.build();
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChainTwitter(HttpSecurity http,
			OAuth2AuthorizationRequestResolver resolver) throws Exception {
		return http.requestMatchers().antMatchers("/pkce/oauth2/authorization/twitter","/login/oauth2/code/twitter")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.authorizationEndpoint(a->a.authorizationRequestResolver(resolver))
				.successHandler(successHandler)
				.authorizedClientService(oAuth2AuthorizedClientService))
			.build();
	}
	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChainFacebook(HttpSecurity http,
			OAuth2AuthorizationRequestResolver resolver) throws Exception {
		return http.requestMatchers().antMatchers("/oauth2/authorization/facebook","/login/oauth2/code/facebook")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.successHandler(successHandler)
				.authorizedClientService(oAuth2AuthorizedClientService))
			.build();
	}
	@Bean
	@Order(3)
	public SecurityFilterChain securityFilterChainLinkedin(HttpSecurity http,
			OAuth2AuthorizationRequestResolver resolver) throws Exception {
		return http.requestMatchers().antMatchers("/oauth2/authorization/linkedin","/login/oauth2/code/linkedin")
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.oauth2Login(auth->
				auth.successHandler(successHandler)
				.authorizedClientService(oAuth2AuthorizedClientService)
				.userInfoEndpoint(info->info.userService(userService))
				.tokenEndpoint(a->a.accessTokenResponseClient(accessTokenResponse)))
			.build();
	}
	@Bean
    public OAuth2AuthorizationRequestResolver pkceResolver(ClientRegistrationRepository repo) {
        DefaultOAuth2AuthorizationRequestResolver resolver = new DefaultOAuth2AuthorizationRequestResolver(repo, "/pkce/oauth2/authorization");
        resolver.setAuthorizationRequestCustomizer(OAuth2AuthorizationRequestCustomizers.withPkce());
        return resolver;
    }

	@Bean
	public AuthenticationManager authManager(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsDervice);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.getPublicKey()).build();
	}

	@Bean
	public JwtEncoder jwtEncoder() {
		JWK jwk = new RSAKey.Builder(jwtConfigProperties.getPublicKey())
				.privateKey(jwtConfigProperties.getPrivateKey())
				.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

}
