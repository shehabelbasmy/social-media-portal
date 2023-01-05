package com.allmedia.portal.facebook.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.dto.response.ConvDto;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.mapper.FacebookConvMapper;
import com.allmedia.portal.facebook.service.FacebookConvService;
import com.allmedia.portal.facebook.service.FacebookPageService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FacebookConvServiceImpl implements FacebookConvService {

	private final FacebookConvMapper fbConvMapper;
	private final FacebookPageService fbPageService;
	
	@Override
	public List<ConvDto> getConv(Long pageId) {
		Set<FacebookPageConv> convs = fbPageService.getConv(pageId);
		return fbConvMapper.mapTo(convs);
	}

}
