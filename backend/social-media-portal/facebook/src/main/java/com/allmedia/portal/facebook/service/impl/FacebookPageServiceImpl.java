package com.allmedia.portal.facebook.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.client.FacebookPageClient;
import com.allmedia.portal.facebook.dto.request.FBPageAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBPageDtoResponse;
import com.allmedia.portal.facebook.entity.FacebookPage;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.entity.FacebookUser;
import com.allmedia.portal.facebook.mapper.FacebookPageMapper;
import com.allmedia.portal.facebook.oauth2.request.FBPageAddPostOauthRequest;
import com.allmedia.portal.facebook.oauth2.request.FBPagePostsOAuthRequest;
import com.allmedia.portal.facebook.oauth2.response.FBPageAddPostOauthResponse;
import com.allmedia.portal.facebook.oauth2.response.FBPagePostsOAuthResponse;
import com.allmedia.portal.facebook.oauth2.response.FBPageTokenOAuthResponse;
import com.allmedia.portal.facebook.repo.FacebookPageRepo;
import com.allmedia.portal.facebook.service.FacebookPageService;
import com.allmedia.portal.facebook.service.FacebookUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacebookPageServiceImpl implements FacebookPageService {

	private final FacebookPageMapper facebookPageMapper;
	private final FacebookPageRepo facebookPageRepo;
	private final FacebookPageClient facebookPageClient;
	private final FacebookUserService facebookUserService;
//	private final WebTokenDetails webTokenDetails;
	
	@Override
	public List<FBPageDtoResponse> getAllPages() {
		FacebookUser facebookUser = facebookUserService.findByUserEmail();
		return facebookPageMapper.mapTo(facebookUser.getFacebookPage());
	}

	@Override
	public List<?> getAllPosts(Long pageId) {
		Optional<FacebookPage> facebookPage=facebookPageRepo.findByPageId(pageId);
		String token = new String(facebookPage.get().getToken());
		var  request=FBPagePostsOAuthRequest.builder().token(token).build(); 
		FBPagePostsOAuthResponse allPosts = facebookPageClient.getAllPosts(request.map(), pageId);
		List<?> resposne=facebookPageMapper.mapTo(allPosts);
		return resposne;
	}

	@Override
	public List<?> addPost(FBPageAddPostRequest dto) {
		Set<FacebookPage> facebookPageToken = facebookPageRepo.findByPageIdIn(dto.getPageIds()).get();
		var reposnes = new ArrayList<FBPageAddPostOauthResponse>();
		facebookPageToken.stream().forEach(item->{
			var request = FBPageAddPostOauthRequest.builder().accessToken(new String(item.getToken())).message(dto.getMessage()).build();
			var response = facebookPageClient.addPost(request.getAsMap(), item.getPageId().toString());
			reposnes.add(response);
		});
		return reposnes;
	}

	@Override
	public void savePages(FBPageTokenOAuthResponse userPages) {
		Set<FacebookPage> facebookPages= facebookPageMapper.mapTo(userPages);
		FacebookUser facebookUser = facebookUserService.findByUserEmail();
		facebookUser.getFacebookPage().clear();
		facebookUser.getFacebookPage().addAll(facebookPages);
		facebookPages.forEach(e->e.setFacebookUser(facebookUser));
		facebookUserService.save(facebookUser);
	}

	@Override
	public Set<FacebookPageConv> getConv(Long pageId) {
//		String facebookUser = webTokenDetails.getFacebookUser();
//		Optional<Set<FacebookPageConv>> result= facebookPageRepo.getPageConv(pageId,facebookUser);
		return facebookPageRepo.findByPageId(pageId)
			.map(e->e.getConversations())
			.orElseGet(null);
	}

}
