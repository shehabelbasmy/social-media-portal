package com.allmedia.portal.facebook.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FBPageDtoResponse {

	
	private Long pageId;

	private String name;
}
