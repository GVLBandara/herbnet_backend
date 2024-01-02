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
	public List<MessageListPreviewDto> listChats(String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found by username" + username));
		return messageRepository.findLastMessagesForEachUser(user.getUserId()).stream().map(message -> messageMapper.toListPreview(message, user)).toList();
	}

	@Override
	public List<MessageDto> fromSender(Long senderId, String username) {
		User sender = userRepository.findById(senderId).orElseThrow(() -> new EntityNotFoundException("User not found by Id" + senderId));
		User receiver = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found by username" + username));
		return messageRepository.findAllBySender(sender, receiver).stream().map(messageMapper::toDto).toList();
	}
}