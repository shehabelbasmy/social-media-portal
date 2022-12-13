package com.socialmedia.portal.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

//	private final UserDetailsService detailsService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
//		super.onAuthenticationSuccess(request,response,authentication);
		String userId = ((DefaultOAuth2User)authentication.getPrincipal()).getAttribute("id");
		String registrationId = ((OAuth2AuthenticationToken)authentication).getAuthorizedClientRegistrationId();
//		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.sendRedirect("/api/v1/auth/"+registrationId+"/"+userId);
//		response.setHeader("Location", "/api/v1/auth/test/"+userId);
	}
	

}
