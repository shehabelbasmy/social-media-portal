package com.allmedia.portal.facebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.service.FacebookUserService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(BaseController.BASE_URI+"/facebook")
@AllArgsConstructor
public class FacebookController {

	private final FacebookUserService facebookUserService;
	@GetMapping("/user/token")
	public String getUserToken() {
		var facebbookUser = facebookUserService.findByUserEmail();
		return new String(facebbookUser.getAccessTokenValue());
	}
	@GetMapping("/page/token")
	public Object getPageToken() {
		var facebbookUser = facebookUserService.findByUserEmail();
		return new String(facebbookUser.getFacebookPage().stream().filter(e->e.getPageId().toString().equals("105636675729201")).findFirst().get().getToken());
	}
	
}
