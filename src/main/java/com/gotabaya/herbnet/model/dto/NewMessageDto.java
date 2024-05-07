package com.gotabaya.herbnet.model.dto;

import java.util.Optional;

public record NewMessageDto(
		Long senderId,
		Long receiverId,
		Optional<Long> productId,
		String messageContent
) {}