package com.allmedia.portal.facebook.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.client.FacebookGroupClient;
import com.allmedia.portal.facebook.dto.request.FBGroupAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBGroupDtoResponse;
import com.allmedia.portal.facebook.entity.FacebookGroup;
import com.allmedia.portal.facebook.entity.FacebookUser;
import com.allmedia.portal.facebook.mapper.FacebookGroupMapper;
import com.allmedia.portal.facebook.oauth2.request.FBGroupAddPostOAuthRequest;
import com.allmedia.portal.facebook.oauth2.response.FBGroupAddPostOAuthResposne;
import com.allmedia.portal.facebook.oauth2.response.FBGroupsOAuthReposne;
import com.allmedia.portal.facebook.service.FacebookGroupService;
import com.allmedia.portal.facebook.service.FacebookUserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class FacebookGroupServiceImpl implements FacebookGroupService {

	private final FacebookUserService facebookUserService;
	private final FacebookGroupMapper facebookGroupMapper;
	private final FacebookGroupClient facebookGroupClient;
	
	@Override
	public List<FBGroupDtoResponse> getAllGroups() {
		FacebookUser facebookUser = facebookUserService.findByUserEmail();
		var groups =facebookGroupMapper.mapTo(facebookUser.getFacebookGroups());
		return groups;
	}

	@Override
	public void saveGroups(FBGroupsOAuthReposne resposne) {
		Set<FacebookGroup> groups = facebookGroupMapper.mapTo(resposne);
		FacebookUser facebookUser = facebookUserService.findByUserEmail();
		facebookUser.getFacebookGroups().clear();
		facebookUser.getFacebookGroups().addAll(groups);
		groups.forEach(e->e.setFacebookUser(facebookUser));
		facebookUserService.save(facebookUser);
	}

	@Override
	public List<?> addGroupPost(FBGroupAddPostRequest dto) {
		var faceUser = facebookUserService.findByUserEmail();
		String token = new String(faceUser.getAccessTokenValue());
		var request = FBGroupAddPostOAuthRequest.builder().message(dto.getMessage()).token(token).build();
		List<FBGroupAddPostOAuthResposne> responses= new ArrayList<>();
		var result = faceUser.getFacebookGroups().stream().filter(e->Arrays.asList(dto.getGroupIds()).contains(e.getGroupId())).toList();
		result.stream().forEach(group->{
			var resposne = facebookGroupClient.addGroupPost(group.getGroupId().toString(),request.map());
			resposne.setGroupName(group.getName());
			responses.add(resposne);
		});
		return facebookGroupMapper.mapTo(responses);
	}

}
