package com.allmedia.portal.facebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.dto.request.FBGroupAddPostRequest;
import com.allmedia.portal.facebook.service.FacebookGroupService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(BaseController.BASE_URI+"/facebook/group")
@AllArgsConstructor
public class FacebookGroupController {

	private final FacebookGroupService facebookGroupService ;
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllGroups(){
		var body= facebookGroupService.getAllGroups();
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/addPost")
	public ResponseEntity<?> addGroupPost(@RequestBody FBGroupAddPostRequest request){
		var body = facebookGroupService.addGroupPost(request);
		return ResponseEntity.ok(body);
	}
}
