package com.allmedia.portal.facebook.oauth2.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FBGroupsOAuthReposne {

	@JsonProperty("data")
	private List<Response> data;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Response{
		
		@JsonProperty("id")
		private Long id;
		
		@JsonProperty("name")
		private String name;
		
	}
}
