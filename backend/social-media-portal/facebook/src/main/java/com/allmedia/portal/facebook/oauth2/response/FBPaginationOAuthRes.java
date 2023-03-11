package com.allmedia.portal.facebook.oauth2.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FBPaginationOAuthRes {

	@JsonProperty("cursors")
	private Cursors cursors;
	
	@Setter@Getter
	@AllArgsConstructor
	@NoArgsConstructor@Builder
	public static class Cursors{
		
		@JsonProperty("before")
		private String before;
		@JsonProperty("after")
		private String after;
	}
}
