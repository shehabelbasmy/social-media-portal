package com.allmedia.portal.facebook.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.dto.response.FBGroupAddPostResposne;
import com.allmedia.portal.facebook.dto.response.FBGroupDtoResponse;
import com.allmedia.portal.facebook.entity.FacebookGroup;
import com.allmedia.portal.facebook.oauth2.response.FBGroupAddPostOAuthResposne;
import com.allmedia.portal.facebook.oauth2.response.FBGroupsOAuthReposne;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FacebookGroupMapper {

	public Set<FacebookGroup> mapTo(FBGroupsOAuthReposne authReposne) {
		return authReposne.getData().stream()
			.map(resposne->FacebookGroup.builder()
				.groupId(resposne.getId())
				.name(resposne.getName())
				.build())
			.collect(Collectors.toSet());
	}
	
	public List<FBGroupDtoResponse> mapTo(Collection<FacebookGroup> list){
		return list.stream()
			.map(e->FBGroupDtoResponse.builder()
				.name(e.getName())
				.groupId(e.getGroupId())
				.build())
			.toList();
	}

	public List<?> mapTo(List<FBGroupAddPostOAuthResposne> responses) {

		return responses.stream().map(reposnse->
				FBGroupAddPostResposne.builder()
				.id(reposnse.getId())
				.updatedTime(reposnse.getUpdatedTime())
				.message(reposnse.getMessage())
				.from(reposnse.getOther())
				.groupName(reposnse.getGroupName())
				.build()
			).toList();
	}
}
