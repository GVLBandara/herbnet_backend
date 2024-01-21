package com.gotabaya.herbnet.model.dto;

public record ProductDto_short(
		Long productId,
		String species,
		String plantOrgan,
		String processingMethod,
		String location,
		String listingDate
) {}
