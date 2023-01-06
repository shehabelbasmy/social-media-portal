package com.allmedia.portal.facebook.service;

import java.util.List;
import java.util.Set;

import com.allmedia.portal.facebook.dto.request.FBPageAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBPageDtoResponse;
import com.allmedia.portal.facebook.entity.FacebookPage;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.oauth2.response.FBPageTokenOAuthResponse;

public interface FacebookPageService {

	List<FBPageDtoResponse> getAllPages();

	List<?> getAllPosts(Long pageId);

	List<?> addPost(FBPageAddPostRequest dto);

	void savePages(FBPageTokenOAuthResponse userPages);

	Set<FacebookPageConv> getConv(Long pageId);

	FacebookPage getPage(Long pageId);


}
