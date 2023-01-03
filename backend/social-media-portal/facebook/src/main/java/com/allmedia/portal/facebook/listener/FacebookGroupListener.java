package com.allmedia.portal.facebook.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.client.FacebookGroupClient;
import com.allmedia.portal.facebook.event.FacebookSuccessLogin;
import com.allmedia.portal.facebook.oauth2.request.FBGroupOAuthRequest;
import com.allmedia.portal.facebook.oauth2.response.FBGroupsOAuthReposne;
import com.allmedia.portal.facebook.service.FacebookGroupService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FacebookGroupListener implements ApplicationListener<FacebookSuccessLogin> {

	private final FacebookGroupService facebookGroupService;
	private final FacebookGroupClient facebookGroupClient;
	
	@Override
	public void onApplicationEvent(FacebookSuccessLogin event) {
		FBGroupOAuthRequest request = FBGroupOAuthRequest.builder()
			.accessToken(event.getToken())
			.build();
		FBGroupsOAuthReposne resposne = facebookGroupClient.getAllGroups(event.getUserId(), request.getAsMap());
		facebookGroupService.saveGroups(resposne);
	}

}
