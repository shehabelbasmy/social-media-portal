package com.allmedia.portal.twitter.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public interface TwitterUserService extends OAuth2AuthorizedClientService {

	void register(String userId);

}
