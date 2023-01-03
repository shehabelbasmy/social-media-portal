package com.allmedia.portal.security.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Embeddable
public class MyOAuth2AuthorizedClientId implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Column(name="client_registration_id")
	private String clientRegistrationId;
	
	
	@Column(name="user_id")
	private String userId;
	
	@Override
	public int hashCode() {
		return Objects.hash(clientRegistrationId+userId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyOAuth2AuthorizedClientId other = (MyOAuth2AuthorizedClientId) obj;
		return clientRegistrationId+userId == other.clientRegistrationId+userId;
	}
	
}
