package com.allmedia.portal.facebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.service.FacebookConvService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(BaseController.BASE_URI+"/facebook/conv")
public class FacebookConvController {

	private final FacebookConvService facebookConvService;
	
	@GetMapping("/list/{pageId}")
	public ResponseEntity<?> getConv(@PathVariable("pageId")Long pageId){
		var body = facebookConvService.getConv(pageId);
		return ResponseEntity.ok(body);
	}
}
