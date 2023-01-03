package com.allmedia.portal.twitter.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterUserRepo extends JpaRepository<TwitterUser, String> {

	Optional<TwitterUser> findByUserEmail(String userId);

	void deleteByUserId(String principalName);

	Optional<TwitterUser> findByUserId(String userId);

}
