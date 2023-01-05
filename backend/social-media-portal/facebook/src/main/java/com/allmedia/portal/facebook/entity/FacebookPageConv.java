package com.allmedia.portal.facebook.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="facebook_page_conversation")
@SuperBuilder
@Setter
@Getter
public class FacebookPageConv extends AbstractEntity{

	@NaturalId
	@Column(name="conv_id")
	private String convId;
	
	@Column(name="from_user_id")
	private String fromUserId;

	@Column(name="to_user_id")
	private String toUserId;
	
	@ManyToOne(cascade = {DETACH,MERGE,PERSIST,PERSIST})
	@JoinColumn(name="facebook_age_id")
	private FacebookPage facebookPage;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacebookPageConv other = (FacebookPageConv) obj;
		return convId.equals( other.getConvId());
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(this.convId);
	}
}
