package com.allmedia.portal.facebook.repo;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allmedia.portal.facebook.entity.FacebookPageToken;

public interface FacebookPageTokenRepo extends JpaRepository<FacebookPageToken, Long> {

	public Set<FacebookPageToken> findByPageIdIn(Long[] pageIds);

	public FacebookPageToken findByPageId(Long pageId);

}
