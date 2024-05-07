package com.gotabaya.herbnet.model.dto;

import java.time.LocalDateTime;
import java.util.Optional;

public record MessageDto(
		Long senderId,
		String message,
		Optional<Long> productId,
		boolean isRead,
		LocalDateTime timestamp
) {}
