package com.socialmedia.portal.security.request;

import java.time.LocalDateTime;

import org.springframework.security.core.token.DefaultToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto{

	private String token;
	
	private LocalDateTime issuedAt;
	
	private LocalDateTime expiredAt;
	
	private String userEmail;
	
	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof DefaultToken) {
			TokenDto rhs = (TokenDto) obj;
			return this.token.equals(rhs.token) && this.issuedAt == rhs.issuedAt
					&& this.userEmail.equals(rhs.userEmail);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int code = 979;
		code = code * this.token.hashCode();
		code = code * Long.valueOf(this.issuedAt.getNano()).hashCode();
		code = code * this.userEmail.hashCode();
		return code;
	}
}
