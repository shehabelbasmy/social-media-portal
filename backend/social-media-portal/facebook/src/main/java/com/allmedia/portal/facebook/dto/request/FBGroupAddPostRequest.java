package com.allmedia.portal.facebook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FBGroupAddPostRequest {

	private String message;
	
	private Long[] groupIds;
	
}
