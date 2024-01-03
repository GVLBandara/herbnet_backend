package com.gotabaya.herbnet.model.dto;

import java.time.LocalDateTime;

public record MessageListPreviewDto(
		String withUser,
		String message,
		LocalDateTime timestamp,
		boolean isRead
) {}
