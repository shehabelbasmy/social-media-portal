package com.allmedia.portal.service;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.framework.repo.UserRepository;
import com.allmedia.portal.security.entity.MyOAuth2AuthorizedClient;
import com.allmedia.portal.security.service.CustomOAuth2AuthorizedClientService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepo;
	private final CustomOAuth2AuthorizedClientService auth2AuthorizedClientService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepo.findByEmail(email);
		User user= optionalUser.orElseThrow(()->new UsernameNotFoundException("Incorrect Username Or Password"));
		
		return new org.springframework.security.core.userdetails.User(
				user.getEmail(),
				user.getPassword(),
				user.getUserDetails().isEnabled(),
				user.getUserDetails().isAccountNonExpired(),
				user.getUserDetails().isCredentialsNonExpired(),
				user.getUserDetails().isAccountNonLocked(),
				user.getRole().stream().map(e->e.getRoleType().toString()).map(SimpleGrantedAuthority::new).collect(Collectors.toSet())
				);
	}
	
	public void updateUser(String email, String userId,String registrationId) {
		MyOAuth2AuthorizedClient authorizedClient = auth2AuthorizedClientService.findAuthorizedClient(userId,registrationId);
		User user = userRepo.findByEmail(email).get();
		authorizedClient.setUser(user);
		user.getAuth2AuthorizedClients().add(authorizedClient);
		userRepo.saveAndFlush(user);
	}
	
	public User findByEmail(String email) {
		return userRepo.findByEmail(email).get();
	}
}
