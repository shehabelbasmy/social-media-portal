package com.allmedia.portal.linkedin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.allmedia.portal.framework.controller.BaseController;

@Component
public class LinkedInSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userId = ((DefaultOAuth2User) authentication.getPrincipal()).getAttribute("id");
		response.sendRedirect(BaseController.BASE_URI+"/linkedin/auth/" + userId);
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
