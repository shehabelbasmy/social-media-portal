package com.allmedia.portal.facebook.oauth2.response;

import java.time.ZonedDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@JsonIgnoreProperties(ignoreUnknown = true)
public class FBGroupAddPostOAuthResposne {

	@JsonProperty("id")
	private String id;
	@JsonProperty("message")
	private String message;
	@JsonProperty("updated_time")
	private ZonedDateTime updatedTime;
	@JsonProperty("from")
	private Map<String,Object> other;
	@JsonIgnore
	private String groupName;
}
