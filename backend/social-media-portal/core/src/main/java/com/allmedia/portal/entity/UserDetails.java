package com.allmedia.portal.entity;

import static javax.persistence.CascadeType.ALL;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.allmedia.portal.framework.entity.AbstractEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@Entity
@Table(name="user_details")
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails extends AbstractEntity{

	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="birth_date")
	private LocalDate birthDate;
	@Column(name="enabled")
	@Builder.Default
	private boolean enabled=true;
	@Builder.Default
	@Column(name="accountNonExpired")
	private boolean accountNonExpired=true;
	@Builder.Default
	@Column(name="credentialsNonExpired")
	private boolean credentialsNonExpired=true;
	@Builder.Default
	@Column(name="accountNonLocked")
	private boolean accountNonLocked=true;
	
	@OneToOne(fetch = FetchType.EAGER,cascade = ALL)
	@JoinColumn(name = "user_id")
	private User user;

}
