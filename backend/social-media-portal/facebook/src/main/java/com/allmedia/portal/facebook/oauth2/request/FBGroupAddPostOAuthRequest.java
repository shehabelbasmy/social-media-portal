package com.allmedia.portal.facebook.oauth2.request;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FBGroupAddPostOAuthRequest {

	private String token;
	private String message;
	public Map<String, String>map(){
		Map<String, String> map = new HashMap<>();
		map.put("access_token", token);
		map.put("message", message);
		map.put("fields", "id,message,updated_time,from");
		return map;
	}
}
