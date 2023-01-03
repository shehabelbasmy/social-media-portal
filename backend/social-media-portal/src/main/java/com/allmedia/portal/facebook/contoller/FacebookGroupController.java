package com.allmedia.portal.facebook.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.service.FacebookGroupService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/facebook/group")
@AllArgsConstructor
public class FacebookGroupController {

	private final FacebookGroupService facebookGroupService ;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllGroups(@CurrentSecurityContext(expression = "authentication") JwtAuthenticationToken authenticationToken){
		var body= facebookGroupService.getAllGroups(authenticationToken.getName());
		return ResponseEntity.ok(body);
	}
}
