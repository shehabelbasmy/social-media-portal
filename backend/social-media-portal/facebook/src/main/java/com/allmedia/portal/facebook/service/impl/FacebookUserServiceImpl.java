package com.allmedia.portal.facebook.service.impl;

import javax.transaction.Transactional;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.stereotype.Service;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.facebook.entity.FacebookUser;
import com.allmedia.portal.facebook.event.FacebookSuccessLogin;
import com.allmedia.portal.facebook.mapper.FacebookUserMapper;
import com.allmedia.portal.facebook.repo.FacebookUserRepo;
import com.allmedia.portal.facebook.service.FacebookUserService;
import com.allmedia.portal.repo.UserRepository;
import com.allmedia.portal.util.WebTokenDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class FacebookUserServiceImpl implements FacebookUserService {

	private final FacebookUserRepo facebookUserRepo;
	private final UserRepository userRepository;
	private final FacebookUserMapper facebookMapper;
	private final ApplicationContext applicationContext;
	private final WebTokenDetails webTokenDetails;
	
	@SuppressWarnings("unchecked")
	@Override
	public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId,
			String userId) {
		FacebookUser facebookUser= facebookUserRepo.findByUserEmail(userId).get();
		return facebookMapper.mapTo(facebookUser);
	}

	@Override
	public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {
		facebookUserRepo.deleteByUserId(authorizedClient.getPrincipalName());
		facebookUserRepo.flush();
		facebookUserRepo.save(facebookMapper.mapTo(authorizedClient));
		
	}

	@Override
	public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
		facebookUserRepo.deleteByUserId(principalName);
	}

	@Override
	public void register(String userId) {
		FacebookUser facebookUser= facebookUserRepo.findByUserId(userId).get();
		String email = webTokenDetails.getEmail();
		User user= userRepository.findByEmail(email).get();
		facebookUser.setUser(user);
		user.getOauth2Client().add(facebookUser);
		userRepository.save(user);
		applicationContext.publishEvent(
			new FacebookSuccessLogin(facebookUser.getUserId(),facebookUser.getAccessTokenValue()));
	}
	
	@Override
	public FacebookUser findByUserEmail() {
		FacebookUser facebookUser= facebookUserRepo.findByUserEmail(webTokenDetails.getEmail()).get();
		return facebookUser;
	}

	@Override
	public FacebookUser save(FacebookUser facebookUser) {
		return facebookUserRepo.save(facebookUser);
	}
	
}
