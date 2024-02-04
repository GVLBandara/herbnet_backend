package com.gotabaya.herbnet.model.dto;

public record ProductDto_long(
		Long productId,
		Long userId,
		String userName,
		String plantName,
		String species,
		String plantOrgan,
		String processingMethod,
		String price,
		String location,
		String description,
		String additionalInformation,
		String harvestDate,
		String listingDate
) {}
