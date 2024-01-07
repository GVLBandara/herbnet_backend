package com.gotabaya.herbnet.mapper;

import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.Message;
import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.model.dto.NewMessageDto;

public interface MessageMapper {
	MessageDto toDto(Message message);
	MessageListPreviewDto toListPreview(Message message, User user);

	Message toEntity(NewMessageDto newMessage);
}
