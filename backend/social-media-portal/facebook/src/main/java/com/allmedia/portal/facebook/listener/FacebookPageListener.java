package com.allmedia.portal.facebook.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.client.FacebookAccesTokenPageClient;
import com.allmedia.portal.facebook.event.FacebookSuccessLogin;
import com.allmedia.portal.facebook.service.FacebookPageService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class FacebookPageListener implements ApplicationListener<FacebookSuccessLogin> {

	private final FacebookAccesTokenPageClient facebookAccesTokenPageClient;
	private final FacebookPageService facebookPageService;

	@Override
	public void onApplicationEvent(FacebookSuccessLogin event) {
		String userId = event.getUserId();
		String token=event.getToken();
		var userPages = facebookAccesTokenPageClient.getPageToken(userId, token);
		facebookPageService.savePages(userPages);
	}

}
