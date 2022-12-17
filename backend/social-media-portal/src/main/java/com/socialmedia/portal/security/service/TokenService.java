package com.socialmedia.portal.security.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.socialmedia.portal.security.response.dto.TokenDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TokenService {

	private final JwtEncoder jwtEncoder;
	
	public TokenDto generateToken(Authentication authenticationToken) {
		return TokenDto.builder()
				.issuedAt(LocalDateTime.now())
				.expiredAt(LocalDateTime.now().plusHours(1))
				.token(generateJwt(authenticationToken))
				.userEmail(authenticationToken.getName())
				.build();
	}
	
	private String generateJwt(Authentication authenticationToken) {
		String scope = authenticationToken.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		JwtClaimsSet claims=JwtClaimsSet.builder()
			.issuedAt(Instant.now())
			.expiresAt(Instant.now().plus(60, ChronoUnit.HOURS))
			.subject(authenticationToken.getName())
			.claim("scope", scope)
			.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}
