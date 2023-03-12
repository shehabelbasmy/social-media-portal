package com.allmedia.portal.facebook.dto.response;

import java.time.LocalDateTime;
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
	private LocalDateTime createdAt;
	private Map<String , Object> others;
	
}
