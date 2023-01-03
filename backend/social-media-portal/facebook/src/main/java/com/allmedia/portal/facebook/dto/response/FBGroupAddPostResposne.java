package com.allmedia.portal.facebook.dto.response;

import java.time.ZonedDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FBGroupAddPostResposne {

	private String id;
	
	private String message;
	
	private String groupName;
	
	private ZonedDateTime updatedTime;
	
	private Map<String, Object> from;
}
