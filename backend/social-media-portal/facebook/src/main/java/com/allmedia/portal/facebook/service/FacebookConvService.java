package com.allmedia.portal.facebook.service;

import java.util.List;

import com.allmedia.portal.facebook.dto.response.ConvDto;

public interface FacebookConvService {

	List<ConvDto> getConv(Long pageId);

}
