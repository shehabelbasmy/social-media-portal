package com.allmedia.portal.facebook.oauth2.request;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FBPageAddPostOauthRequest {

	private String message;
	private String accessToken;
	
	public Map<String, String> getAsMap(){
		return Map.of("message", this.message, 
			"access_token", this.accessToken,
			"fields","created_time,id,message");
	}
	
}
