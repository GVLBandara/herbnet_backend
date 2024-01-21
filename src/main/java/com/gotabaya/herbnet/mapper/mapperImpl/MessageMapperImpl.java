package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.MessageMapper;
import com.gotabaya.herbnet.model.Message;
import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.model.dto.NewMessageDto;
import com.gotabaya.herbnet.model.dto.UserProfileDto;
import com.gotabaya.herbnet.repository.ProductRepository;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {
	final UserProfileService userProfileService;
	final UserRepository userRepository;
	final ProductRepository productRepository;
	@Override
	public MessageDto toDto(Message message) {
		return new MessageDto(
				message.getSender().getUserId(),
				message.getMessageContent(),
				message.isRead(),
				message.getTimestamp()
		);
	}

	@Override
	public MessageListPreviewDto toListPreview(Message message, User user) {
		UserProfileDto senderProfile;
		Long withUserId = (message.getSender().equals(user))? message.getReceiver().getUserId():message.getSender().getUserId();
		senderProfile = userProfileService.findById(withUserId);
		String withUserName = senderProfile.firstName() + " " + senderProfile.lastName();
		return new MessageListPreviewDto(
				withUserId,
				withUserName,
				message.getMessageContent(),
				message.getTimestamp(),
				message.isRead()
		);
	}

	@Override
	public Message toEntity(NewMessageDto newMessage) {
		Product product = productRepository.findById(newMessage.productId()).orElse(null);
		return new Message(
				null,
				null,
				null,
				product,
				newMessage.messageContent(),
				LocalDateTime.now(),
				false
		);
	}
}