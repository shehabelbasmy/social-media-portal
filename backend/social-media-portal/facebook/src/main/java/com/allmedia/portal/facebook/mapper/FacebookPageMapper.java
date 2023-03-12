package com.allmedia.portal.facebook.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.dto.response.FBPageDtoResponse;
import com.allmedia.portal.facebook.dto.response.FBPagePostsResponse;
import com.allmedia.portal.facebook.entity.FacebookPage;
import com.allmedia.portal.facebook.oauth2.response.FBPagePostsOAuthResponse;
import com.allmedia.portal.facebook.oauth2.response.FBPageTokenOAuthResponse;

@Component
public class FacebookPageMapper {

	public Set<FacebookPage> mapTo(FBPageTokenOAuthResponse userPages) {
		return userPages.getResposne().stream()
				.map(resposne->FacebookPage.builder()
					.name(resposne.getName())
					.pageId(resposne.getPageId())
					.token(resposne.getToken().getBytes())
					.build()).collect(Collectors.toSet());
	}
	public List<FBPageDtoResponse> mapTo(Collection<FacebookPage> list){
		return list.stream()
			.map(e->FBPageDtoResponse.builder()
				.name(e.getName())
				.pageId(Long.valueOf(e.getPageId()))
				.build()
			).toList();
	}
	public List<?> mapTo(FBPagePostsOAuthResponse allPosts) {
		
		return allPosts.getRespose()
			.stream().map(item->
				FBPagePostsResponse.builder()
				.createdAt(item.getCreatedAt().toLocalDateTime())
				.id(item.getId())
				.message(item.getMessage())
				.others(item.getAddParamter())
				.build()
			).toList();
	}
}
