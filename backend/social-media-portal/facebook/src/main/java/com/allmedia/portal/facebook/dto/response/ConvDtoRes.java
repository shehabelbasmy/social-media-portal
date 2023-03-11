package com.allmedia.portal.facebook.dto.response;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConvDtoRes {

	
	private String id;

	private String fromName;

	private ZonedDateTime updatedTime;

}
