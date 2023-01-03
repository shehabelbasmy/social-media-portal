package com.allmedia.portal.facebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FBGroupAddPostResponse {

	private String id;
	
	private String message;
}
