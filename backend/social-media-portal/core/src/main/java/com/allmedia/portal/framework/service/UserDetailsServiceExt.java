package com.allmedia.portal.framework.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.allmedia.portal.entity.User;

public interface UserDetailsServiceExt extends UserDetailsService {

	User getUserByEmail(String email);

}
