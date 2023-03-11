package com.allmedia.portal.facebook.dto.response;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessagesDtoRes {
	
	private List<ListDto> list;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class ListDto{
		private String message;
		
		private String from;
		
		private ZonedDateTime createdTime;
	}
	
	private String next;
}
