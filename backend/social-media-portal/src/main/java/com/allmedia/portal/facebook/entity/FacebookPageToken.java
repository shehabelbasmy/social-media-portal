package com.allmedia.portal.facebook.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.allmedia.portal.framework.entity.AbstractEntity;
import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="facebook_page_token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacebookPageToken extends AbstractEntity{

	@NaturalId
	@Column(name="page_id")
	private Long pageId;
	
	@Column(name="token")
	@Lob
	private byte[] token;

	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumns({@JoinColumn(name = "user_id"),@JoinColumn(name = "registration_id")})
	private MyOAuth2AuthorizedClient auth2AuthorizedClient;
	
	@Override
	public int hashCode() {
		return Objects.hashCode(pageId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookPageToken other = (FacebookPageToken) obj;
		return pageId.equals(other.pageId);
	}
}
