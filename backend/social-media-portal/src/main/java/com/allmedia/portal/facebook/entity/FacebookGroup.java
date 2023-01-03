package com.allmedia.portal.facebook.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity@Table(name="facebook_group")
@AllArgsConstructor@Builder
@NoArgsConstructor
public class FacebookGroup extends AbstractEntity {

	private Long goupId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
}
