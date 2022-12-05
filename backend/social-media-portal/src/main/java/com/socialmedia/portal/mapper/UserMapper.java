package com.socialmedia.portal.mapper;

import org.springframework.stereotype.Component;

import com.socialmedia.portal.entity.User;
import com.socialmedia.portal.security.request.AuthenticationRequest;

@Component
public class UserMapper {

	public User mapTo(AuthenticationRequest authenticationRequest) {
		
		return null;
	}
}
