package com.allmedia.portal.twitter.mapper;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.stereotype.Component;

import com.allmedia.portal.twitter.repo.TwitterUser;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class TwitterUserMapper {
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	
	public OAuth2AuthorizedClient mapTo(TwitterUser linkedInUser) {
		String clientRegistrationId = "twitter";
		ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
		String principalName = linkedInUser.getUserId();
		OAuth2AccessToken accessToken = mapToToken(linkedInUser);
		OAuth2AuthorizedClient auth2AuthorizedClient = new OAuth2AuthorizedClient(clientRegistration, principalName,
				accessToken);
		return auth2AuthorizedClient;
	}
	private OAuth2AccessToken mapToToken(TwitterUser facebookUser) {

		OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(TokenType.BEARER,
				new String(facebookUser.getAccessTokenValue()),
				facebookUser.getAccessTokenIssuedAt().toInstant(ZoneOffset.UTC),
				facebookUser.getAccessTokenExpiredAt().toInstant(ZoneOffset.UTC));
		return oAuth2AccessToken;
	}
	
	public TwitterUser mapTo(OAuth2AuthorizedClient authorizedClient) {
		return TwitterUser.builder()
			.userId(authorizedClient.getPrincipalName())
			.accessTokenValue(authorizedClient.getAccessToken().getTokenValue().getBytes())
			.accessTokenIssuedAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getIssuedAt(), ZoneOffset.UTC))
			.accessTokenExpiredAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getExpiresAt(), ZoneOffset.UTC))
			.build();
	}
	
}
