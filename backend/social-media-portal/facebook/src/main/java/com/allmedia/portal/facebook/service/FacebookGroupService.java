package com.allmedia.portal.facebook.service;

import java.util.List;

import com.allmedia.portal.facebook.dto.request.FBGroupAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBGroupDtoResponse;
import com.allmedia.portal.facebook.oauth2.response.FBGroupsOAuthReposne;

public interface FacebookGroupService {

	public List<FBGroupDtoResponse> getAllGroups();

	public void saveGroups(FBGroupsOAuthReposne resposne);

	public List<?> addGroupPost(FBGroupAddPostRequest request);

}
