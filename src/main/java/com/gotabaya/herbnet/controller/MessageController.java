package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.MessageDto;
import com.gotabaya.herbnet.model.dto.MessageListPreviewDto;
import com.gotabaya.herbnet.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("/{senderId}")
	List<MessageDto> withSender(@PathVariable(name = "senderId") Long senderId, Principal principal){
		return messageService.fromSender(senderId, principal.getName());
	}
}
