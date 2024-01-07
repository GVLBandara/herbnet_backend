package com.gotabaya.herbnet.model.dto;

public record NewMessageDto(
		Long senderId,
		Long receiverId,
		Long productId,
		String messageContent
) {}