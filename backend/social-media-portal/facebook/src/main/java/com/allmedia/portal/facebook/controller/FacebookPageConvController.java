package com.allmedia.portal.facebook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allmedia.portal.facebook.service.FacebookPageConvService;
import com.allmedia.portal.framework.controller.BaseController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(BaseController.BASE_URI+"/facebook/conv")
public class FacebookPageConvController {

	private final FacebookPageConvService facebookConvService;
	
	@GetMapping("/list/{pageId}")
	public ResponseEntity<?> getConv(@PathVariable("pageId")Long pageId){
		var body = facebookConvService.getConv(pageId);
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/list/sync/{pageId}")
	public ResponseEntity<?> getConvSync(@PathVariable("pageId")Long pageId){
		var body = facebookConvService.getConvOfPageSync(pageId);
		return ResponseEntity.ok(body);
	}
	
	@GetMapping("/messages/{convId}")
	public ResponseEntity<?> getMessages(@PathVariable("convId")String convId,
			@RequestParam(value = "next",required = false)String next){
		var body =facebookConvService.getMessages(convId,next);
		return ResponseEntity.ok(body);
	}
}
