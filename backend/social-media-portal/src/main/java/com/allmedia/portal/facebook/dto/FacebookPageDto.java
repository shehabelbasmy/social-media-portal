package com.allmedia.portal.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookPageDto {

	private Long pageId;
	
	private String name;
}
