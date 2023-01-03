package com.allmedia.portal.linkedin.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.linkedin.entity.LinkedInUser;

public interface LinkedinUserRepo extends JpaRepository<LinkedInUser, Long> {

	Optional<LinkedInUser> findByUserEmail(String userId);

	void deleteByUserId(String principalName);

	Optional<LinkedInUser> findByUserId(String userId);

}
