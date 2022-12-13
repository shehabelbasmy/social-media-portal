package com.socialmedia.portal.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.socialmedia.portal.security.entity.MyOAuth2AuthorizedClientId;


public interface OAuth2AuthorizedClientRepo extends JpaRepository<MyOAuth2AuthorizedClient, MyOAuth2AuthorizedClientId> {

	public Optional<MyOAuth2AuthorizedClient> findById(MyOAuth2AuthorizedClientId id);
}
