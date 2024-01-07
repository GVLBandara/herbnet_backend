package com.gotabaya.herbnet.model.dto;

import java.time.LocalDateTime;

public record MessageListPreviewDto(
		Long withUserId,
		String withUserName,
		String message,
		LocalDateTime timestamp,
		boolean isRead
) {}
