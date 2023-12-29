package com.gotabaya.herbnet.model.dto;

import java.time.LocalDateTime;

public record MessageDto(
		Long messageId,
		String sender,
		String message,
		LocalDateTime timestamp
) {}
