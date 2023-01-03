package com.allmedia.portal.facebook.dto.response;

import java.time.ZonedDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FBPagePostsResponse {

	private String id;
	private String message;
	private ZonedDateTime createdAt;
	private Map<String , Object> others;
	
}
