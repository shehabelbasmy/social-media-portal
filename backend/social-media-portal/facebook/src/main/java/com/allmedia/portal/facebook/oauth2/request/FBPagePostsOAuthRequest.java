package com.allmedia.portal.facebook.oauth2.request;

import java.util.HashMap;
import java.util.Map;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FBPagePostsOAuthRequest {

	private String token;
	
	@Setter(value = AccessLevel.NONE)
	private String fields;
	
	public Map<String, String> map() {
		Map<String, String> map  = new HashMap<>();
		map.put("access_token", token);
		map.put("fields", "created_time,message,reactions.summary(total_count).limit(0)");
		return map; 
	}
}
