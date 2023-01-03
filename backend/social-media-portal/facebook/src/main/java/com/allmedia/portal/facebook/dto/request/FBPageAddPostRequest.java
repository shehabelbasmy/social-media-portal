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
public class FBPageAddPostRequest {

	private String message;
	
	private Long[] pageIds;
	
}
