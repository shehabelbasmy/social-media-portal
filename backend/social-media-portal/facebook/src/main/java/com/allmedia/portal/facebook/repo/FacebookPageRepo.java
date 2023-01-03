package com.allmedia.portal.facebook.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.facebook.entity.FacebookPage;

public interface FacebookPageRepo extends JpaRepository<FacebookPage, String> {

	Optional<Set<FacebookPage>> findByPageIdIn(Long[] pageIds);

	Optional<FacebookPage> findByPageId(Long pageId);

}
