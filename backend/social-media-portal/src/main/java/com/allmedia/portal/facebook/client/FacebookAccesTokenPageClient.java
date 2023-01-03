package com.allmedia.portal.facebook.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.facebook.dto.response.FacebookPageTokenResponse;

@FeignClient(url = "${facebook.base-url}",name = "facebook-access-token-page")
public interface FacebookAccesTokenPageClient {

	@GetMapping(value = "/{userId}/accounts")
	public FacebookPageTokenResponse getPageToken(@PathVariable(value="userId")String userId,
		@RequestParam(value = "access_token")String accessToken);
}
