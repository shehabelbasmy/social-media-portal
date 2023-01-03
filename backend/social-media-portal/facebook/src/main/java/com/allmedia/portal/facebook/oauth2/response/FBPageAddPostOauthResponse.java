package com.allmedia.portal.facebook.oauth2.response;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown =  true)
public class FBPageAddPostOauthResponse {

	@JsonProperty("created_time")
	private ZonedDateTime dateTime;
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("message")
	private String message;
}
