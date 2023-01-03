package com.allmedia.portal.security.response.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
}
