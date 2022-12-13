package com.socialmedia.portal.security.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.socialmedia.portal.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="oauth2_authorized_client_1")
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@Builder
public class MyOAuth2AuthorizedClient {

	@EmbeddedId
	private MyOAuth2AuthorizedClientId id;
	
	@Column(name="access_token_type")
	private String accessTokenType;
	
	@Lob
	@Column(name="access_token_value",columnDefinition = "BLOB")
	private String accessTokenValue;
	
	@Column(name="access_token_issued_at")
	private LocalDateTime accessTokenIssuedAt;
	
	@Column(name="access_token_expired_at")
	private LocalDateTime accessTokenExpiredAt;
	
	@Column(name="access_token_scopes")
	private String accessTokenScopes;
	
	@Lob
	@Column(name="refresh_token_value",columnDefinition = "BLOB")
	private String refreshTokenValue;
	
	@Column(name="refresh_token_issued_at")
	private LocalDateTime refreshTokenIssuedAt;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {DETACH,MERGE,PERSIST,REFRESH})
	@JoinColumn(name = "user_entity_id")
	private User user;
	
	@Override
	public int hashCode() {
		return Objects.hash(id.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		return id.equals(obj);
	}
	
}
