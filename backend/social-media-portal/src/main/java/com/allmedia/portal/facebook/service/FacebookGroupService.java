package com.allmedia.portal.facebook.service;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.repo.FacebookGroupRepo;
import com.allmedia.portal.security.service.CustomOAuth2AuthorizedClientService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class FacebookGroupService {

	private final CustomOAuth2AuthorizedClientService auth2AuthorizedClientService;
	private final FacebookGroupRepo facebookGroupRepo;
	
	public ResponseEntity<?> getAllGroups(String name) {
		
		return null;
	}
	
//	public List<E>
}
