package com.allmedia.portal.facebook.oauth2.request;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FBPageConvOAuthRequest {

	private String accessToken;
	
	
	public Map<String, String>getAsMap(){
		Map<String, String> map =new HashMap<>();
		map.put("access_token", accessToken);
		map.put("fields", "id,participants,updated_time");
		map.put("limit", "25");
		return map;
	}
}
