package com.allmedia.portal.linkedin.security;

import java.util.Collections;

import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

import com.allmedia.portal.linkedin.client.LinkedInAccessTokenClient;
import com.allmedia.portal.linkedin.oauth.request.LinkedInOAuth2JWTTokenRequest;
import com.allmedia.portal.linkedin.oauth.response.LinkedInOAuth2JWTTokenReposne;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LinkedInOAuth2AccessTokenResponseClient
		implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

	private final LinkedInAccessTokenClient linkedInClient;

	@Override
	public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
		LinkedInOAuth2JWTTokenRequest request = createRequest(authorizationGrantRequest);
		return map(linkedInClient.exhangeCode(request.getAsMap()));
	}

	private LinkedInOAuth2JWTTokenRequest createRequest(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
		return LinkedInOAuth2JWTTokenRequest.builder()
				.clientId(authorizationGrantRequest.getClientRegistration().getClientId())
				.clientSecret(authorizationGrantRequest.getClientRegistration().getClientSecret())
				.grantType(authorizationGrantRequest.getGrantType().getValue())
				.redirectUri(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse()
						.getRedirectUri())
				.code(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode())
				.build();
	}

	private OAuth2AccessTokenResponse map(LinkedInOAuth2JWTTokenReposne accessTokenReposne) {

		return OAuth2AccessTokenResponse.withToken(accessTokenReposne.getAccessToken())
				.expiresIn(accessTokenReposne.getExpiresIn()).refreshToken("").tokenType(TokenType.BEARER)
				.scopes(Collections.emptySet()).build();

	}

}
