package com.allmedia.portal.security.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClientId;


public interface OAuth2AuthorizedClientRepo extends JpaRepository<MyOAuth2AuthorizedClient, MyOAuth2AuthorizedClientId> {

	public Optional<MyOAuth2AuthorizedClient> findById(MyOAuth2AuthorizedClientId id);
	public Optional<Set<MyOAuth2AuthorizedClient>> findByUserEmail(String email);
}
