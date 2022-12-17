package com.socialmedia.portal.security.request.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2JWTTokenRequest {

	private String grantType;
	private String redirectUri;
	private String clientSecret;
	private String clientId;
	private String code;
	
	public Map<String, String> getAsMap() {
		Map<String, String> map =new HashMap<>();
		map.put("grant_type", grantType);
		map.put("redirect_uri", redirectUri);
		map.put("client_secret", clientSecret);
		map.put("client_id", clientId);
		map.put("code", code);
		return map;
	}
	
}
