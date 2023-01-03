package com.allmedia.portal.facebook.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

import com.allmedia.portal.facebook.entity.FacebookUser;

public interface FacebookUserService extends OAuth2AuthorizedClientService{

	void register(String userId);

	FacebookUser findByUserEmail();

	FacebookUser save(FacebookUser facebookUser);

	
}
