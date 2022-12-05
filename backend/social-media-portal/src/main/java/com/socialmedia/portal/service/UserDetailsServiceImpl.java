package com.socialmedia.portal.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.socialmedia.portal.entity.User;
import com.socialmedia.portal.framework.repo.UserRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepo;
	
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

}
