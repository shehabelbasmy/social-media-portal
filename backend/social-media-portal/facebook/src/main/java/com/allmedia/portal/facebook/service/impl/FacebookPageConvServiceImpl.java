package com.allmedia.portal.facebook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.allmedia.portal.facebook.client.FaceboookPageConvClient;
import com.allmedia.portal.facebook.dto.response.ConvDtoRes;
import com.allmedia.portal.facebook.dto.response.MessagesDtoRes;
import com.allmedia.portal.facebook.entity.FacebookPage;
import com.allmedia.portal.facebook.entity.FacebookPageConv;
import com.allmedia.portal.facebook.mapper.FacebookPageConvMapper;
import com.allmedia.portal.facebook.oauth2.request.FBPageConvMessagesOAuthRequest;
import com.allmedia.portal.facebook.oauth2.request.FBPageConvOAuthRequest;
import com.allmedia.portal.facebook.repo.FacebookPageConvRepo;
import com.allmedia.portal.facebook.service.FacebookPageConvService;
import com.allmedia.portal.facebook.service.FacebookPageService;
import com.allmedia.portal.util.WebTokenDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class FacebookPageConvServiceImpl implements FacebookPageConvService {

	private final FacebookPageConvMapper fbConvMapper;
	private final FaceboookPageConvClient client;
	private final FacebookPageConvRepo convRepo;
	private final WebTokenDetails webTokenDetails;
	private final FacebookPageService pageService;
	
	@Override
	public List<ConvDtoRes> getConv(Long pageId) {
		String email = webTokenDetails.getEmail();
		Optional<Set<FacebookPageConv>> convs = 
			convRepo.findByFacebookPageFacebookUserUserEmailAndFacebookPagePageIdOrderByUpdatedAtAsc(email, pageId);
		if (convs.isPresent()) {
			return fbConvMapper.mapToDto(convs.get());
		}
		return List.of();
	}

	@Override
	public List<ConvDtoRes> getConvOfPageSync(Long pageId) {
		FacebookPage page = pageService.getPage(pageId);
		if (page!=null) {
			var request= FBPageConvOAuthRequest.builder().accessToken(new String(page.getToken())).build();
			var resposne =client.getConv(page.getPageId().toString(), request.getAsMap());
			Set<FacebookPageConv> convs = fbConvMapper.mapToEntity(resposne);
			convs.stream().forEach(conv->conv.setFacebookPage(page));
			page.getConversations().addAll(convs);
			convRepo.saveAll(page.getConversations().stream().toList());
			return fbConvMapper.mapToDto(convs);
		}
		return List.of();
	}

	@Override
	public MessagesDtoRes getMessages(String convId,String next) {
		var result =convRepo.findByFacebookPageFacebookUserUserEmailAndConvId(webTokenDetails.getEmail(),convId);
		if (result.isPresent()) {
			String token = new String(result.get().getFacebookPage().getToken());
			var request = FBPageConvMessagesOAuthRequest.builder().next(next).token(token).build();
			var resposne =client.getConvMessages(convId,request.getAsMap());
			MessagesDtoRes dto= fbConvMapper.mapToMessages(resposne);
			return dto;
		}
		return null;
	}
}
