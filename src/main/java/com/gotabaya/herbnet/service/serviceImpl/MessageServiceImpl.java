package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.mapper.MessageMapper;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.repository.MessageRepository;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
	final MessageRepository messageRepository;
	final UserRepository userRepository;
	final MessageMapper messageMapper;

	@Override
	public List<MessageListPreviewDto> listChats() {
		return messageRepository.findLastMessagesForEachUser().stream().map(messageMapper::toListPreview).toList();
	}

	@Override
	public List<MessageDto> fromSender(Long senderId) {
		User sender = userRepository.findById(senderId).orElseThrow(() -> new EntityNotFoundException("User not found by Id" + senderId));
		return messageRepository.findAllBySender(sender).stream().map(messageMapper::toDto).toList();
	}
}