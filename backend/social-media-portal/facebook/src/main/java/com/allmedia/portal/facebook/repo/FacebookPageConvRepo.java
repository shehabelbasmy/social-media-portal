package com.allmedia.portal.facebook.repo;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.allmedia.portal.facebook.entity.FacebookPageConv;

public interface FacebookPageConvRepo extends JpaRepository<FacebookPageConv, Long> ,PagingAndSortingRepository<FacebookPageConv, Long>{

	Optional<FacebookPageConv> findByFacebookPageFacebookUserUserEmailAndConvId(String email,String convId);

	Optional<Set<FacebookPageConv>> findByFacebookPageFacebookUserUserEmailAndFacebookPagePageIdOrderByUpdatedAtAsc(String email, Long pageId);

}
