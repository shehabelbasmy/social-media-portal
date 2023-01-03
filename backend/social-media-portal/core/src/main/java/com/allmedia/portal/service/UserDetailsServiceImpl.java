package com.allmedia.portal.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.allmedia.portal.entity.User;
import com.allmedia.portal.framework.service.UserDetailsServiceExt;
import com.allmedia.portal.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsServiceExt {

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
