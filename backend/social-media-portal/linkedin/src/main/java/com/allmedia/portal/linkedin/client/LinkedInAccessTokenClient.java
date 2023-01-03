package com.allmedia.portal.linkedin.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.linkedin.oauth.response.LinkedInOAuth2JWTTokenReposne;

@FeignClient(url = "${linkedin.baseUrl}",name="a", path = "/")
public interface LinkedInAccessTokenClient {

	@GetMapping(value = "/oauth/v2/accessToken",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public LinkedInOAuth2JWTTokenReposne exhangeCode(@RequestParam Map<String, String> request);

}
