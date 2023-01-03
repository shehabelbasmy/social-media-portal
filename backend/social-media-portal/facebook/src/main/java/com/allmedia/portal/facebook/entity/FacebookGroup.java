package com.allmedia.portal.facebook.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "facebook_group")
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
public class FacebookGroup extends AbstractEntity {

	@NaturalId
	@Column(name = "group_id")
	private Long groupId;

	@Column(name="name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
		FacebookGroup other = (FacebookGroup) obj;
		return groupId.equals(other.groupId);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.groupId);
	}
}
