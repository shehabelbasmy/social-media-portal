package com.allmedia.portal.facebook.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.entity.FacebookPageToken;
import com.allmedia.portal.facebook.repo.FacebookPageTokenRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacebookPageTokenService {

	private final FacebookPageTokenRepo pageTokenRepo;
	
	public Set<FacebookPageToken> findByPageIdIn(Long[] pageId) {
		return pageTokenRepo.findByPageIdIn(pageId);
	}

	public FacebookPageToken findById(Long pageId) {
		return pageTokenRepo.findByPageId(pageId);
	}
}
