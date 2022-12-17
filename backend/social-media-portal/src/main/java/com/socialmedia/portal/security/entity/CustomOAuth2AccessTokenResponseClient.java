package com.socialmedia.portal.security.entity;

import java.util.Collections;

import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.stereotype.Service;

import com.socialmedia.portal.security.client.LinkedInClient;
import com.socialmedia.portal.security.request.dto.OAuth2JWTTokenRequest;
import com.socialmedia.portal.security.response.dto.OAuth2JWTTokenReposne;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2AccessTokenResponseClient
		implements OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> {

	
	private final LinkedInClient linkedInClient;
	
	@Override
	public OAuth2AccessTokenResponse getTokenResponse(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
		
		OAuth2JWTTokenRequest request = createRequest(authorizationGrantRequest);
		return map(linkedInClient.exhangeCode(request.getAsMap()));
	}
	
	private OAuth2JWTTokenRequest createRequest(OAuth2AuthorizationCodeGrantRequest authorizationGrantRequest) {
		return OAuth2JWTTokenRequest.builder()
			.clientId(authorizationGrantRequest.getClientRegistration().getClientId())
			.clientSecret(authorizationGrantRequest.getClientRegistration().getClientSecret())
			.grantType(authorizationGrantRequest.getGrantType().getValue())
			.redirectUri(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getRedirectUri())
			.code(authorizationGrantRequest.getAuthorizationExchange().getAuthorizationResponse().getCode())
			.build();
	}
	
	private OAuth2AccessTokenResponse map(OAuth2JWTTokenReposne accessTokenReposne) {
		
		return OAuth2AccessTokenResponse.withToken(accessTokenReposne.getAccessToken())
			.expiresIn(accessTokenReposne.getExpiresIn())
			.refreshToken("")
			.tokenType(TokenType.BEARER)
			.scopes(Collections.emptySet())
			.build();
		
	}

}
