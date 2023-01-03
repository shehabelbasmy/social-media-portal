package com.allmedia.portal.twitter.repo;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.allmedia.portal.framework.entity.OAuth2Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity@Table(name="twitter_user")
@NoArgsConstructor
@SuperBuilder
@Setter@Getter
public class TwitterUser extends OAuth2Client{

}
