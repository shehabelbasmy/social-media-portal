package com.allmedia.portal.facebook.oauth2.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FBPageTokenOAuthResponse {

	@JsonProperty("data")
	private Set<Response> resposne;
	
	@Setter
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Response{
		@JsonProperty("name")
		private String name;
		@JsonProperty("access_token")
		private String token;
		@JsonProperty("id")
		private Long pageId;
	}
	
}