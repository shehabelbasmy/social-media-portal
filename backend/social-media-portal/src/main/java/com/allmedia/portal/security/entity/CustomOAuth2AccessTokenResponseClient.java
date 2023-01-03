package com.allmedia.portal.security.entity;

import java.util.Collections;

import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

import com.allmedia.portal.security.client.LinkedInClient;
import com.allmedia.portal.security.request.dto.LinkedInOAuth2JWTTokenRequest;
import com.allmedia.portal.security.response.dto.LinkedInOAuth2JWTTokenReposne;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2AccessTokenResponseClient
		implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

	
	private final LinkedInClient linkedInClient;
	
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
			.redirectUri(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getRedirectUri())
			.code(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode())
			.build();
	}
	
	private OAuth2AccessTokenResponse map(LinkedInOAuth2JWTTokenReposne accessTokenReposne) {
		
		return OAuth2AccessTokenResponse.withToken(accessTokenReposne.getAccessToken())
			.expiresIn(accessTokenReposne.getExpiresIn())
			.refreshToken("")
			.tokenType(TokenType.BEARER)
			.scopes(Collections.emptySet())
			.build();
		
	}

}
