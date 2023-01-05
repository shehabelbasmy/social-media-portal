package com.allmedia.portal.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
@RequestScope
public class WebTokenDetails {

	private String email;
	
	private List<String> roles;
	
	private String facebookUser;
	
	@PostConstruct
	private void init() {
		var authenticationToken= (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
		this.email=authenticationToken.getName();
		this.roles=authenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
		
	}
	
}
