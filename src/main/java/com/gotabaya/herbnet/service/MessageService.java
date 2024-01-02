package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;

import java.util.List;

public interface MessageService {
	List<MessageListPreviewDto> listChats(String username);
	List<MessageDto> fromSender(Long senderId, String username);
}
