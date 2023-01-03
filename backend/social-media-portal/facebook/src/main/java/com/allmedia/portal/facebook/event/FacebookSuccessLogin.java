package com.allmedia.portal.facebook.event;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;

public class FacebookSuccessLogin extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	
	@Getter
	private String userId;
	@Getter
	private String token;
	
	public FacebookSuccessLogin(String userId,byte[] token) {
		super(userId);
		this.userId= userId;
		this.token = new String(token);
	}
	
}
