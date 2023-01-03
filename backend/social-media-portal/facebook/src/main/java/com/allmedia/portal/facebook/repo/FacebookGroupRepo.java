package com.allmedia.portal.facebook.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allmedia.portal.facebook.entity.FacebookGroup;

public interface FacebookGroupRepo extends JpaRepository<FacebookGroup, Long> {

	@Query(value="select g.groupId from FacebookGroup g where g.groupId in (:groupIds) and g.facebookUser.userId =:userId")
	Optional<List<Long>> existsByGroupIdIn(Long[] groupIds,String userId);

}
