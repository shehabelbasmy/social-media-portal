package com.allmedia.portal.facebook.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.client.FacebookPageClient;
import com.allmedia.portal.facebook.dto.FacebookPageAddPostDto;
import com.allmedia.portal.facebook.dto.FacebookPageDto;
import com.allmedia.portal.facebook.dto.FacebookPagePostResponse.Response;
import com.allmedia.portal.facebook.dto.request.FacebookPageAddPostRequest;
import com.allmedia.portal.facebook.entity.FacebookPageToken;
import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.allmedia.portal.security.service.CustomOAuth2AuthorizedClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacebookPageService {

	private final FacebookPageClient facebookPageClient;
	private final FacebookPageTokenService pageTokenService;
	private final OAuth2AuthorizedClientService auth2AuthorizedClientService;
//	private final PageMapper pageMapper;
	
	public void addPost(FacebookPageAddPostDto dto) {
		Set<FacebookPageToken> facebookPageToken = pageTokenService.findByPageIdIn(dto.getPageIds());
		facebookPageToken.stream().forEach(item->{
			FacebookPageAddPostRequest request = FacebookPageAddPostRequest.builder().accessToken(new String(item.getToken())).message(dto.getMessage()).build();
			facebookPageClient.addPost(request.getAsMap(), item.getPageId());
		});
	}

	public List<FacebookPageDto> getAllPages(JwtAuthenticationToken authenticationToken) {
		String email = authenticationToken.getName();
		Set<MyOAuth2AuthorizedClient> authorizedClient= ((CustomOAuth2AuthorizedClientService)auth2AuthorizedClientService)
			.findByUserEmail(email);
		return authorizedClient.stream()
			.filter(e->e.getId().getClientRegistrationId().equals("facebook"))
			.findFirst().get()
			.getFacebookPageToken().stream()
			.map(e->
				FacebookPageDto.builder()
				.pageId(e.getPageId())
				.name(e.getName())
				.build())
			.toList();
	}

	public Set<Response> getAllPosts(Long pageId) {
		String token =new String(pageTokenService.findById(pageId).getToken());
		return facebookPageClient.getAllPosts(
			Map.of("access_token", token,"fields","message,reactions.summary(total_count).limit(0)"), pageId).getRespose();
	}
}
