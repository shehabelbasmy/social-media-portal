package com.allmedia.portal.linkedin.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.allmedia.portal.framework.entity.OAuth2Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity@Table(name="linkedin_user")
//@AllArgsConstructor
@NoArgsConstructor
@Setter@Getter
@SuperBuilder
public class LinkedInUser extends OAuth2Client {

	
}
