package com.allmedia.portal.facebook.mapper;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.dto.response.ConvDto;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.oauth2.response.FBPageConvResposne;

@Component
public class FacebookConvMapper {

	public List<ConvDto> mapTo(Collection<FacebookPageConv> col){
		return col.stream().map(item->
				ConvDto.builder()
				.build()
			).toList();
				
	}
	
	public List<ConvDto> mapTo(FBPageConvResposne resposne){
		return resposne.getData().stream().map(item->
				ConvDto.builder()
				.id(item.getId())
				.dateTime(item.getUpdatedTime())
				.fromName(item.getParticipants().getData().get(0).getName())
				.build()
			).toList();
				
	}
	
}
