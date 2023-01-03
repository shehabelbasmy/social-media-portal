package com.allmedia.portal.facebook.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${facebook.base-url}",name="facebook-group-client")
public interface FacebookGroupClient {

//	@GetMapping("/{userId}/groups")
//	public 
}
