package com.allmedia.portal.facebook.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="facebook_page_conv")
@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FacebookPageConv extends AbstractEntity{

	@NaturalId
	@Column(name="conv_id")
	private String convId;
	
	@Column(name="from_user_name")
	private String fromUserName;
	
	@ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST})
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
		return Objects.hash(this.convId);
	}
}
