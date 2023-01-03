package com.allmedia.portal.facebook.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.facebook.oauth2.response.FBGroupAddPostOAuthResposne;
import com.allmedia.portal.facebook.oauth2.response.FBGroupsOAuthReposne;

@FeignClient(url = "${facebook.base-url}",name="facebook-group-client")
public interface FacebookGroupClient {

	@GetMapping("/{userId}/groups")
	public FBGroupsOAuthReposne getAllGroups(@PathVariable("userId")String userId,
		@RequestParam Map<String, String> requets);

	@PostMapping("/{groupId}/feed")
	public FBGroupAddPostOAuthResposne addGroupPost(@PathVariable("groupId")String goupId,
		@RequestParam Map<String, String> request);
}
