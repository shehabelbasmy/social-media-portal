package com.allmedia.portal.facebook.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.dto.response.ConvDto;
import com.allmedia.portal.facebook.entity.FacebookPageConv;

@Component
public class FacebookConvMapper {

	public List<ConvDto> mapTo(Collection<FacebookPageConv> col){
		return col.stream().map(item->
				ConvDto.builder()
				.build()
			).toList();
				
	}
	
}
