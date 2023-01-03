package com.allmedia.portal.facebook.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.allmedia.portal.facebook.dto.FacebookPagePostResponse;

@FeignClient(url = "${facebook.base-url}",name="facebook-page-client")
public interface FacebookPageClient {
	
	@PostMapping("/{pageId}/feed")
	public String addPost(@RequestParam Map<String, String> request,
		@PathVariable("pageId")Long pageid);

	@GetMapping("/{pageId}/published_posts")
	public FacebookPagePostResponse getAllPosts(@RequestParam Map<String, String> request,
		@PathVariable("pageId")Long pageId);
}
