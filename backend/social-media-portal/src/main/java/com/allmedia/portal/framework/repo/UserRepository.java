package com.allmedia.portal.framework.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public Optional<User> findByEmail(String email);

}
