package com.allmedia.portal.facebook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.dto.request.FBPageAddPostRequest;
import com.allmedia.portal.facebook.dto.response.FBPageDtoResponse;
import com.allmedia.portal.facebook.service.FacebookPageService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(BaseController.BASE_URI+"/facebook/page")
@AllArgsConstructor
public class FacebookPageController {

	private final FacebookPageService facebookPageService;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllPages(){
		List<FBPageDtoResponse> pages = facebookPageService.getAllPages();
		return ResponseEntity.ok(pages);
	}
	
	@PostMapping("/addpost")
	public ResponseEntity<?> addPost(@RequestBody FBPageAddPostRequest dto){
		var resposne =facebookPageService.addPost(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(resposne);
	}
	
	@GetMapping("/posts/{pageId}")
	public ResponseEntity<?> getAllPosts(@PathVariable("pageId")Long pageId){
		List<?> posts=facebookPageService.getAllPosts(pageId);
		return ResponseEntity.ok(posts);
	}
	
}
