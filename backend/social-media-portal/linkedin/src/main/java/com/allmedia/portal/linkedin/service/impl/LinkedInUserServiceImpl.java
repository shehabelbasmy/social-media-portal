package com.allmedia.portal.linkedin.service.impl;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.linkedin.entity.LinkedInUser;
import com.allmedia.portal.linkedin.mapper.LinkedinUserMapper;
import com.allmedia.portal.linkedin.repo.LinkedinUserRepo;
import com.allmedia.portal.linkedin.service.LinkedinUserService;
import com.allmedia.portal.repo.UserRepository;
import com.allmedia.portal.util.WebTokenDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class LinkedInUserServiceImpl implements LinkedinUserService {

	private final LinkedinUserRepo linkedInUserRepo;
	private final LinkedinUserMapper linkedinUserMapper;
	private final UserRepository userRepository;
	private final WebTokenDetails webTokenDetails;
	
	@SuppressWarnings("unchecked")
	@Override
	public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId,
			String userId) {
		LinkedInUser linkedInUser= linkedInUserRepo.findByUserEmail(userId).get();
		return linkedinUserMapper.mapTo(linkedInUser);
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		linkedInUserRepo.deleteByUserId(authorizedClient.getPrincipalName());
		linkedInUserRepo.flush();
		linkedInUserRepo.save(linkedinUserMapper.mapTo(authorizedClient));
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		linkedInUserRepo.deleteByUserId(principalName);
	}

	@Override
	public void register(String userId) {
		var linkedin= linkedInUserRepo.findByUserId(userId).get();
		String email = webTokenDetails.getEmail();
		User user= userRepository.findByEmail(email).get();
		linkedin.setUser(user);
		user.getOauth2Client().add(linkedin);
		userRepository.save(user);
	}

}
