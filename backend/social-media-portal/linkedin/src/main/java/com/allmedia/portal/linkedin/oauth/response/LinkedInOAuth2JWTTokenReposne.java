package com.allmedia.portal.linkedin.oauth.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter@AllArgsConstructor
@NoArgsConstructor
public class LinkedInOAuth2JWTTokenReposne {

	@JsonProperty("access_token")
	private String accessToken;
	
	@JsonProperty("expires_in")
	private Long expiresIn;
	
	@JsonProperty("scope")
	private String scope;
}
