package com.allmedia.portal.facebook.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;
import static javax.persistence.FetchType.EAGER;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.allmedia.portal.framework.entity.OAuth2Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity@Table(name="facebook_user")
@AllArgsConstructor@NoArgsConstructor
@Setter@Getter
@SuperBuilder
public class FacebookUser extends OAuth2Client {

	@OneToMany(mappedBy = "facebookUser",
			cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = EAGER,orphanRemoval = true)
	private Set<FacebookPage> facebookPage;
	
	@OneToMany(mappedBy = "facebookUser",
			cascade = {MERGE,DETACH,PERSIST,REFRESH},fetch = EAGER,orphanRemoval = true)
	private Set<FacebookGroup> facebookGroups;
	
}
