package com.allmedia.portal.facebook.service;

import java.util.List;

import com.allmedia.portal.facebook.dto.response.ConvDtoRes;
import com.allmedia.portal.facebook.dto.response.MessagesDtoRes;

public interface FacebookPageConvService {

	List<ConvDtoRes> getConv(Long pageId);

	MessagesDtoRes getMessages(String convId,String next);

	List<ConvDtoRes> getConvOfPageSync(Long e);

}
