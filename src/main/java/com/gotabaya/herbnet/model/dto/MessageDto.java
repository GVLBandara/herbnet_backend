package com.gotabaya.herbnet.model.dto;

import java.time.LocalDateTime;

public record MessageDto(
		Long senderId,
		String message,

		boolean isRead,
		LocalDateTime timestamp
) {}
