package com.gotabaya.herbnet.model.dto;

import com.gotabaya.herbnet.model.Species;

public record ProductDto_long(
		Long productId,
		Long userId,
		String userName,
		String plantName,
		Species species,
		String plantOrgan,
		String processingMethod,
		String price,
		String location,
		String description,
		String additionalInformation,
		String harvestDate,
		String listingDate
) {}
