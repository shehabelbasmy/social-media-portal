package com.allmedia.portal.framework.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import com.allmedia.portal.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
//@MappedSuperclass
@Entity
public abstract class OAuth2Client {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="id")
	private Long id;
	
	@NaturalId
	@Column(name="user_id")
	private String userId;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@Column(name="access_token_value",columnDefinition = "bytea")
	private byte[] accessTokenValue;
	
	@Column(name="access_token_issued_at")
	private LocalDateTime accessTokenIssuedAt;
	
	@Column(name="access_token_expired_at")
	private LocalDateTime accessTokenExpiredAt;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = {DETACH,MERGE,PERSIST,REFRESH})
	@JoinColumn(name = "user_entity_id")
	private User user;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OAuth2Client other = (OAuth2Client) obj;
		return userId.equals(other.userId);
	}
}
