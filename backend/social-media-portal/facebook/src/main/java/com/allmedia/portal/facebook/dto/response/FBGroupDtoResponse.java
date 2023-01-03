package com.allmedia.portal.facebook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FBGroupDtoResponse {

	private Long groupId;
	
	private String name;
	
}
