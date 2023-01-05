package com.allmedia.portal.facebook.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allmedia.portal.facebook.entity.FacebookPage;
import com.allmedia.portal.facebook.entity.FacebookPageConv;

public interface FacebookPageRepo extends JpaRepository<FacebookPage, String> {

	Optional<Set<FacebookPage>> findByPageIdIn(Long[] pageIds);

	Optional<FacebookPage> findByPageId(Long pageId);

	@Query(value="select page from FacebookPage page where page.pageId = (:pageId) and page.facebookUser.userId =:facebookUser")
	Optional<Set<FacebookPageConv>> getPageConvs(Long pageId, String facebookUser);

}
