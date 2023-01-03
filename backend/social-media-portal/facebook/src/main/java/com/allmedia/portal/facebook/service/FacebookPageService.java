package com.allmedia.portal.facebook.service;

import java.util.List;

import com.allmedia.portal.facebook.dto.request.FBPageAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBPageDtoResponse;
import com.allmedia.portal.facebook.oauth2.response.FBPageTokenOAuthResponse;

public interface FacebookPageService {

	List<FBPageDtoResponse> getAllPages();

	List<?> getAllPosts(Long pageId);

	List<?> addPost(FBPageAddPostRequest dto);

	void savePages(FBPageTokenOAuthResponse userPages);


}
