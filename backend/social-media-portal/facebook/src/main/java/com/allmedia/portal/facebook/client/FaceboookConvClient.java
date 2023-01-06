package com.allmedia.portal.facebook.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.facebook.oauth2.response.FBPageConvResposne;

@FeignClient(url = "${facebook.base-url}",name = "facebook-conversation")
public interface FaceboookConvClient {

	@GetMapping("{pageId}/conversations")
	public FBPageConvResposne getConv(@PathVariable("pageId")String pageid,
		@RequestParam Map<String, String> request);
}
