package com.allmedia.portal.facebook.mapper;

import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.allmedia.portal.facebook.dto.response.ConvDtoRes;
import com.allmedia.portal.facebook.dto.response.MessagesDtoRes;
import com.allmedia.portal.facebook.dto.response.MessagesDtoRes.ListDto;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.oauth2.response.FBPageConvMessageOAuthResposne;
import com.allmedia.portal.facebook.oauth2.response.FBPageConvOAuthResposne;

@Component
public class FacebookPageConvMapper {

	public Set<FacebookPageConv> mapToEntity(FBPageConvOAuthResposne resposne){
		return resposne.getData().stream().map(item->
			FacebookPageConv.builder()
			.convId(item.getId())
			.fromUserName(item.getParticipants().getData().get(0).getName())
			.updatedAt(item.getUpdatedTime().toLocalDateTime())
			.build()
		).collect(Collectors.toSet());
	}
	
	public List<ConvDtoRes> mapToDto(Set<FacebookPageConv> entities){
		return entities.stream().map(item->
			ConvDtoRes.builder()
			.id(item.getConvId())
			.updatedTime(item.getUpdatedAt().atZone(ZoneId.systemDefault()))
			.fromName(item.getFromUserName())
			.build()
		).toList();
	}

	public MessagesDtoRes mapToMessages(FBPageConvMessageOAuthResposne resposne) {
		
		List<ListDto> list = resposne.getResposnes().stream().map(e->
			MessagesDtoRes.ListDto.builder()
			.message(e.getMessage())
			.from(e.getFrom().getName())
			.createdTime(e.getCreatedAt())
			.build()
		).toList();
		
		return MessagesDtoRes.builder()
			.next(resposne.getPage()!=null?resposne.getPage().getCursors().getAfter():null)
			.list(list).build();
	}
	
}
