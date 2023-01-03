package com.allmedia.portal.entity;

import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.allmedia.portal.framework.constant.RoleType;
import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@Builder@AllArgsConstructor@NoArgsConstructor
@Entity@Table(name="role")
public class Role extends AbstractEntity{
	
	@Column(name="role_type")
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = {DETACH,MERGE,PERSIST,REFRESH})
	@JoinTable(name="user_role",
		joinColumns = @JoinColumn(name="role_id")
		,inverseJoinColumns = @JoinColumn(name="user_id"))
	
	private Set<User> user;
}
