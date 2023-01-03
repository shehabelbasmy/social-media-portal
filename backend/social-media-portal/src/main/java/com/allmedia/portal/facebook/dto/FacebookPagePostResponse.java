package com.allmedia.portal.facebook.dto;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

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
public class FacebookPagePostResponse {

	@JsonProperty("data")
	private Set<Response> respose;
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class Response{
		@JsonProperty("id")
		private String id;
		
		@JsonProperty("message")
		private String message;
		
		@JsonProperty("created_time")
		private ZonedDateTime createdAt;
		
		@JsonProperty("reactions")
		private Map<String, Object> addParamter; 
	}
}
