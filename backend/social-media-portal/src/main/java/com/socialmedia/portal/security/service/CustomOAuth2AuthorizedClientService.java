package com.socialmedia.portal.security.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;
import org.springframework.stereotype.Service;

import com.socialmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.socialmedia.portal.security.entity.MyOAuth2AuthorizedClientId;
import com.socialmedia.portal.security.repo.OAuth2AuthorizedClientRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {
	private final OAuth2AuthorizedClientRepo authorizedClientRepository;
	private final ClientRegistrationRepository clientRegistrationRepository;

	@SuppressWarnings("unchecked")
	@Override
	public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId,
			String email) {
		Set<MyOAuth2AuthorizedClient> set = authorizedClientRepository.findByUserEmail(email).get();
		MyOAuth2AuthorizedClient myOAuth2AuthorizedClient = 
		set.stream()
			.filter(e->e.getId().getClientRegistrationId().equals(clientRegistrationId))
			.findFirst().get();
		return (T) mapToOAuth2AuthorizedClient(myOAuth2AuthorizedClient);
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		authorizedClientRepository.save(mapToMyOAuth2AuthorizedClient(authorizedClient,principal));

	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		MyOAuth2AuthorizedClientId id = 
			MyOAuth2AuthorizedClientId.builder()
			.clientRegistrationId(clientRegistrationId)
			.build();
		MyOAuth2AuthorizedClient entity = MyOAuth2AuthorizedClient.builder().id(id).build();
		authorizedClientRepository.delete(entity);
	}
	
	public MyOAuth2AuthorizedClient findAuthorizedClient(String userId,String registrationId) {
		MyOAuth2AuthorizedClientId id = MyOAuth2AuthorizedClientId.builder()
			.clientRegistrationId(registrationId)
			.userId(userId)
			.build();
		return authorizedClientRepository.findById(id).get();
	}
	
	private OAuth2AuthorizedClient mapToOAuth2AuthorizedClient(MyOAuth2AuthorizedClient myOAuth2AuthorizedClient) {
		String clientRegistrationId = myOAuth2AuthorizedClient.getId().getClientRegistrationId().toLowerCase();
		ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
		String principalName = myOAuth2AuthorizedClient.getId().getUserId();
		OAuth2AccessToken accessToken=mapToToken(myOAuth2AuthorizedClient);
		OAuth2AuthorizedClient auth2AuthorizedClient =
			new OAuth2AuthorizedClient(
				clientRegistration,
				principalName,
				accessToken);
		return auth2AuthorizedClient;
	}
	
	private OAuth2AccessToken mapToToken(MyOAuth2AuthorizedClient auth2AuthorizedClient) {
		
		OAuth2AccessToken oAuth2AccessToken=new OAuth2AccessToken(
			TokenType.BEARER,
			auth2AuthorizedClient.getAccessTokenValue(),
			auth2AuthorizedClient.getAccessTokenIssuedAt().toInstant(ZoneOffset.UTC),
			auth2AuthorizedClient.getAccessTokenExpiredAt().toInstant(ZoneOffset.UTC));
		return oAuth2AccessToken;
	}
	
	private MyOAuth2AuthorizedClient mapToMyOAuth2AuthorizedClient(OAuth2AuthorizedClient authorizedClient,Authentication authentication) {
		MyOAuth2AuthorizedClientId id = MyOAuth2AuthorizedClientId.builder()
			.clientRegistrationId(authorizedClient.getClientRegistration().getClientName().toLowerCase())
			.userId(authorizedClient.getPrincipalName())
			.build();
		MyOAuth2AuthorizedClient build = MyOAuth2AuthorizedClient.builder()
			.id(id)
			.accessTokenType(authorizedClient.getAccessToken().getTokenType().getValue())
			.accessTokenValue(authorizedClient.getAccessToken().getTokenValue())
			.accessTokenIssuedAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getIssuedAt(), ZoneOffset.UTC))
			.accessTokenExpiredAt(LocalDateTime.ofInstant(authorizedClient.getAccessToken().getExpiresAt(), ZoneOffset.UTC))
			.build();
		return build;
	}
}