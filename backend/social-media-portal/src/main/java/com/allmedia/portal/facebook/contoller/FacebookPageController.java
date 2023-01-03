package com.allmedia.portal.facebook.contoller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.dto.FacebookPageAddPostDto;
import com.allmedia.portal.facebook.dto.FacebookPageDto;
import com.allmedia.portal.facebook.service.FacebookPageService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/facebook/page")
@AllArgsConstructor
public class FacebookPageController {

	private final FacebookPageService facebookPageService;

	@GetMapping("/list")
	public ResponseEntity<?> getAllPages(@CurrentSecurityContext(expression="authentication")JwtAuthenticationToken authenticationToken){
		List<FacebookPageDto> pages = facebookPageService.getAllPages(authenticationToken);
		return ResponseEntity.ok(pages);
	}
	
	@PostMapping("/addpost")
	public ResponseEntity<?> addPost(@RequestBody FacebookPageAddPostDto dto){
		facebookPageService.addPost(dto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping("/posts/{pageId}")
	public ResponseEntity<?> getAllPosts(@PathVariable("pageId")Long pageId){
		Set<?> posts=facebookPageService.getAllPosts(pageId);
		return ResponseEntity.ok(posts);
	}
	
}
