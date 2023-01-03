package com.allmedia.portal.linkedin.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

public interface LinkedinUserService extends OAuth2AuthorizedClientService {

	void register(String userId);

}
