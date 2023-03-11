package com.allmedia.portal.facebook.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.facebook.oauth2.response.FBPageConvMessageOAuthResposne;
import com.allmedia.portal.facebook.oauth2.response.FBPageConvOAuthResposne;

@FeignClient(url = "${facebook.base-url}",name = "facebook-conversation")
public interface FaceboookPageConvClient {

	@GetMapping("{pageId}/conversations")
	public FBPageConvOAuthResposne getConv(@PathVariable("pageId")String pageid,
		@RequestParam Map<String, String> request);

	@GetMapping("{convId}/messages")
	public FBPageConvMessageOAuthResposne getConvMessages(@PathVariable("convId") String convId,
		@RequestParam Map<String, String> request);
}
