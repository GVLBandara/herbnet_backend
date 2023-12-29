package com.gotabaya.herbnet.model.dto;

public record ProductDto_long(
		Long productId,
		Long userId,
		String userName,
		String plantName,
		String species,
		String plantOrgan,
		String processingMethod,
		String description,
		String listingDate
) {}
