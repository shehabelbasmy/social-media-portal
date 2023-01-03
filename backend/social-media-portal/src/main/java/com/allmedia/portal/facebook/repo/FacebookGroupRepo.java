package com.allmedia.portal.facebook.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.facebook.entity.FacebookGroup;

public interface FacebookGroupRepo extends JpaRepository<FacebookGroup, Long> {

}
