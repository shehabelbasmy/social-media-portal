package com.allmedia.portal.security.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.facebook.entity.FacebookPageToken;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="oauth2_authorized_client")
@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@Builder
public class MyOAuth2AuthorizedClient {

	@EmbeddedId
	private MyOAuth2AuthorizedClientId id;
	
	@Column(name="access_token_type")
	private String accessTokenType;
	
//	@Lob
	@Column(name="access_token_value",columnDefinition = "bytea")
	private byte[] accessTokenValue;

	@Column(name="access_token_issued_at")
	private LocalDateTime accessTokenIssuedAt;
	
	@Column(name="access_token_expired_at")
	private LocalDateTime accessTokenExpiredAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {DETACH,MERGE,PERSIST,REFRESH})
	@JoinColumn(name = "user_entity_id")
	private User user;
	
	@OneToMany(mappedBy = "auth2AuthorizedClient",
		cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = FetchType.EAGER,orphanRemoval = true)
	private Set<FacebookPageToken> facebookPageToken;
	
	@Override
	public int hashCode() {
		return Objects.hash(id.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		return id.equals(obj);
	}

}
