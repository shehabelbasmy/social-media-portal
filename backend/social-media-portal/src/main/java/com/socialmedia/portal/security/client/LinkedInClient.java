package com.socialmedia.portal.security.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialmedia.portal.security.response.dto.OAuth2JWTTokenReposne;

@FeignClient(url = "https://www.linkedin.com",name="a", path = "/")
public interface LinkedInClient {

//	@RequestMapping(method = RequestMethod.POST, value = "/oauth/v2/accessToken",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
//	public OAuth2JWTTokenReposne exhangeCode(@RequestBody OAuth2JWTTokenRequest accessTokenRequest);
	
	@GetMapping(value = "/oauth/v2/accessToken",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public OAuth2JWTTokenReposne exhangeCode(@RequestParam Map<String, String> request);

}
