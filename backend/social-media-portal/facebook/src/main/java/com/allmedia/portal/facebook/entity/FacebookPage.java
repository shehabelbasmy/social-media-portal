package com.allmedia.portal.facebook.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="facebook_page")@Entity
@AllArgsConstructor@NoArgsConstructor
@Setter@Getter@Builder
public class FacebookPage extends AbstractEntity{
	
	@NaturalId
	@Column(name="page_id")
	private Long pageId;

	
	@Column(name="token",columnDefinition = "bytea")
//	@Lob
	private byte[] token;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade = {DETACH,MERGE,PERSIST,REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name = "facebook_user_id")
	private FacebookUser facebookUser;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookPage other = (FacebookPage) obj;
		return pageId.equals( other.pageId);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.pageId);
	}
}
