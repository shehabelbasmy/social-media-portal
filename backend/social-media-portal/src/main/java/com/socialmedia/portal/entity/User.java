package com.socialmedia.portal.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.socialmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`user`")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter@Getter
public class User extends AbstractEntity{

	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {DETACH,MERGE,PERSIST,REFRESH})
	@JoinTable(name="user_role",
		joinColumns = @JoinColumn(name="user_id")
		,inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<Role> role;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	private UserDetails userDetails;
}
