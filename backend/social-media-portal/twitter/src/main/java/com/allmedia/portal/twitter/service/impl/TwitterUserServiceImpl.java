package com.allmedia.portal.twitter.service.impl;

import javax.transaction.Transactional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.repo.UserRepository;
import com.allmedia.portal.twitter.mapper.TwitterUserMapper;
import com.allmedia.portal.twitter.repo.TwitterUser;
import com.allmedia.portal.twitter.repo.TwitterUserRepo;
import com.allmedia.portal.twitter.service.TwitterUserService;
import com.allmedia.portal.util.WebTokenDetails;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class TwitterUserServiceImpl implements TwitterUserService {

	private final TwitterUserMapper twitterUserMapper;
	private final TwitterUserRepo twitterUserRepo;
	private final WebTokenDetails webTokenDetails;
	private final UserRepository userRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId,
			String userId) {
		TwitterUser twitterUser= twitterUserRepo.findByUserEmail(userId).get();
		return twitterUserMapper.mapTo(twitterUser);	
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		twitterUserRepo.deleteByUserId(authorizedClient.getPrincipalName());
		twitterUserRepo.flush();
		twitterUserRepo.save(twitterUserMapper.mapTo(authorizedClient));
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		twitterUserRepo.deleteByUserId(principalName);
	}

	@Override
	public void register(String userId) {
		var twitterUser= twitterUserRepo.findByUserId(userId).get();
		String email = webTokenDetails.getEmail();
		User user= userRepository.findByEmail(email).get();
		twitterUser.setUser(user);
		user.getOauth2Client().add(twitterUser);
		userRepository.save(user);
	}
}
