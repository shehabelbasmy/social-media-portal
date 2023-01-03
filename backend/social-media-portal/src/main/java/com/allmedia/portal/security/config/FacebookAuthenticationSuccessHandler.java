package com.allmedia.portal.security.config;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.client.FacebookAccesTokenPageClient;
import com.allmedia.portal.facebook.dto.response.FacebookPageTokenResponse;
import com.allmedia.portal.facebook.entity.FacebookPageToken;
import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.allmedia.portal.security.service.CustomOAuth2AuthorizedClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacebookAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final FacebookAccesTokenPageClient client;
	private final OAuth2AuthorizedClientService auth2AuthorizedClient;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String userId = ((DefaultOAuth2User) authentication.getPrincipal()).getAttribute("id");
		String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
		MyOAuth2AuthorizedClient authorizedClient = ((CustomOAuth2AuthorizedClientService) auth2AuthorizedClient)
				.findAuthorizedClient(userId, registrationId);
		FacebookPageTokenResponse res= client.getPageToken(userId, new String(authorizedClient.getAccessTokenValue()));
		authorizedClient.setFacebookPageToken(map(res,authorizedClient));
		((CustomOAuth2AuthorizedClientService)auth2AuthorizedClient).update(authorizedClient);
		response.sendRedirect("/api/v1/auth/" + registrationId + "/" + userId);
		super.onAuthenticationSuccess(request, response, authentication);
	}

//	private void createPageToken(MyOAuth2AuthorizedClient authorizedClient,FacebookPageTokenResponse res) {
//		authorizedClient.setFacebookPageToken(map(res,authorizedClient));
//		if (authorizedClient.getFacebookPageToken() == null) {
//			authorizedClient.setFacebookPageToken();
//			return;
//		}
//		authorizedClient.getFacebookPageToken().addAll(map(res,authorizedClient));
//	}
	private Set<FacebookPageToken> map(FacebookPageTokenResponse dto, MyOAuth2AuthorizedClient authorizedClient) {
		return dto.getResposne().stream().map(e->
			FacebookPageToken.builder()
			.name(e.getName())
			.pageId(e.getPageId())
			.auth2AuthorizedClient(authorizedClient)
			.token(e.getToken().getBytes())
			.build()
		).collect(Collectors.toSet());
	}
	

}
