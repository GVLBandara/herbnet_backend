package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.model.dto.NewMessageDto;

import java.util.List;

public interface MessageService {
	List<MessageListPreviewDto> listChats(String username);
	List<MessageDto> fromSender(Long senderId, String username);

	List<MessageDto> newMessage(NewMessageDto newMessageDto, String username);
}
