package com.allmedia.portal.facebook.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.entity.FacebookUser;

@Component
public class FacebookUserMapper {

	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;

	public OAuth2AuthorizedClient mapTo(FacebookUser facebookUser) {
		String clientRegistrationId = "facebook";
		ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
		String principalName = facebookUser.getUserId();
		OAuth2AccessToken accessToken = mapToToken(facebookUser);
		OAuth2AuthorizedClient auth2AuthorizedClient = new OAuth2AuthorizedClient(clientRegistration, principalName,
				accessToken);
		return auth2AuthorizedClient;
	}

	private OAuth2AccessToken mapToToken(FacebookUser facebookUser) {

		OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(TokenType.BEARER,
				new String(facebookUser.getAccessTokenValue()),
				facebookUser.getAccessTokenIssuedAt().toInstant(ZoneOffset.UTC),
				facebookUser.getAccessTokenExpiredAt().toInstant(ZoneOffset.UTC));
		return oAuth2AccessToken;
	}
	
	
	public FacebookUser mapTo(OAuth2AuthorizedClient authorizedClient) {
		return FacebookUser.builder()
			.userId(authorizedClient.getPrincipalName())
			.accessTokenValue(authorizedClient.getAccessToken().getTokenValue().getBytes())
			.accessTokenIssuedAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getIssuedAt(), ZoneOffset.UTC))
			.accessTokenExpiredAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getExpiresAt(), ZoneOffset.UTC))
			.facebookPage(Set.of())
			.build();
	}

}
