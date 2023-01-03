package com.allmedia.portal.facebook.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.facebook.entity.FacebookUser;

public interface FacebookUserRepo extends JpaRepository<FacebookUser, String> {

	Optional<FacebookUser> findByUserId(String userId);
	
	Optional<FacebookUser> findByUserEmail(String email);

	boolean existsByUserId(String principalName);

	void deleteByUserId(String principalName);


}
