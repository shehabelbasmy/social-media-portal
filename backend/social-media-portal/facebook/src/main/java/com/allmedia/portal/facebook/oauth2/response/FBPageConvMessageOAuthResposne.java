package com.allmedia.portal.facebook.oauth2.response;

import java.time.ZonedDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class FBPageConvMessageOAuthResposne {

	@JsonProperty("data")
	private List<Resposne> resposnes;
	
	@Builder
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Resposne{
		
		@JsonProperty("from")
		private From from;

		@Builder
		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class From{
			private String name;
		}
		
		@JsonProperty("message")
		private String message;

		@JsonProperty("created_time")
		private ZonedDateTime createdAt;
	}
	@JsonProperty("paging")
	private FBPaginationOAuthRes page;
}
