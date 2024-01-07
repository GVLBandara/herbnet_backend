package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.model.dto.NewMessageDto;
import com.gotabaya.herbnet.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
	final MessageService messageService;

	@GetMapping("")
	List<MessageListPreviewDto> listChats(Principal principal){
		return messageService.listChats(principal.getName());
	}

	@GetMapping("/{userId}")
	List<MessageDto> withUser(@PathVariable(name = "userId") Long senderId, Principal principal){
		return messageService.fromSender(senderId, principal.getName());
	}

	@PostMapping("")
	List<MessageDto> toUser(@RequestBody NewMessageDto newMessage, Principal principal){
		return messageService.newMessage(newMessage, principal.getName());
	}
}