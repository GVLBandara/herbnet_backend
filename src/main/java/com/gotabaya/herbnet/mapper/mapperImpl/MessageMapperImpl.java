package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.MessageMapper;
import com.gotabaya.herbnet.model.Message;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.model.dto.UserProfileDto;
import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.repository.ProductRepository;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {
	final UserProfileService userProfileService;
	final UserRepository userRepository;
	final ProductRepository productRepository;
	@Override
	public MessageDto toDto(Message message) {
		return new MessageDto(
				message.getMessageId(),
				message.getSender().getUsername(),
				message.getMessageContent(),
				message.getTimestamp()
		);
	}

	@Override
	public MessageListPreviewDto toListPreview(Message message, User user) {
		UserProfileDto senderProfile;
		if(message.getSender().equals(user)){
			senderProfile = userProfileService.findById(message.getReceiver().getUserId());
		}else {
			senderProfile = userProfileService.findById(message.getSender().getUserId());
		}
		String SenderName = senderProfile.firstName() + " " + senderProfile.lastName();

		return new MessageListPreviewDto(
				SenderName,
				message.getMessageContent(),
				message.isRead(),
				message.getTimestamp()
		);
	}
}
