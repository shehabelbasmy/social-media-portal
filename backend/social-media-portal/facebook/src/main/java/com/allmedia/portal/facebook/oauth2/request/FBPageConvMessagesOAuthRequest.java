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
public class FBPageConvMessagesOAuthRequest {

	private String token;
	private String next;
	public Map<String, String> getAsMap() {
		Map<String, String> map =new HashMap<>();
		map.put("access_token", token);
		map.put("limit", "25");
		map.put("fields", "from,message,created_time");
		if (next != null) {
			map.put("after", next);
		}
		return map;
	}
}
