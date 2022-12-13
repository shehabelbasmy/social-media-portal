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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
//(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class SecurityConfig {

	private final UserDetailsService userDetailsDervice;
	private final RsaKeyProperties jwtConfigProperties;
	private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;
	private final AuthenticationSuccessHandler successHandler;
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
			AuthenticationManager authenticationManager) throws Exception {
//		http.addFilterBefore(new BearerTokenAuthenticationFilter(authenticationManager),OAuth2AuthorizationRequestRedirectFilter.class);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return http.csrf().disable()
//			.requestMatcher(new AntPathRequestMatcher("/api/**"))
			.authorizeRequests(
				auth -> auth.antMatchers("/api/v1/auth/**").permitAll()
					.anyRequest().authenticated())
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//			.oauth2Client()
//			.authorizationCodeGrant()
//			.and()
//			.authorizedClientService(oAuth2AuthorizedClientService)
//			.and()
			.oauth2Login()
			.defaultSuccessUrl("/api/v1/auth/test")
//			.permitAll(true)
			.successHandler(successHandler)
			.authorizedClientService(oAuth2AuthorizedClientService)
			.and()
			.build();
	}
//	@Bean
//	@Order(1)
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
//		return httpSecurity
//				.authorizeRequests().anyRequest().authenticated()
//				.and()
//				.oauth2Login().loginPage("/oauth2/authorization/**")
//				.authorizedClientService(oAuth2AuthorizedClientService)
//				.successHandler(successHandler)
//				.and()
//				.build();
//	}
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
		JWK jwk = new RSAKey.Builder(jwtConfigProperties.getPublicKey()).privateKey(jwtConfigProperties.getPrivateKey())
				.build();
		JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}

//	@Bean
//	public CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(List.of("https://localhost:3000"));
//		configuration.setAllowedHeaders(List.of("*"));
//		configuration.setAllowedMethods(List.of("GET"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
}
