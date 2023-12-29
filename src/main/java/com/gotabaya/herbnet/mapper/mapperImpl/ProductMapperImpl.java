package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.ProductMapper;
import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {
	final UserProfileService userProfileService;
	DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	DateTimeFormatter date_time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


	@Override
	public ProductDto_short toDto_short(Product product) {
		return new ProductDto_short(
				product.getProductId(),
				product.getSpecies().getCommonName(),
				product.getSpecies().getScientificName(),
				product.getPlantOrgan(),
				product.getProcessingMethod(),
				product.getListingDate().format(date)
		);
	}

	@Override
	public ProductDto_long toDto_long(Product product) {
		Long userId = product.getUser().getUserId();
		String name = userProfileService.findById(userId).firstName();
		return new ProductDto_long(
				product.getProductId(),
				userId,
				name,
				product.getSpecies().getCommonName(),
				product.getSpecies().getScientificName(),
				product.getPlantOrgan(),
				product.getProcessingMethod(),
				product.getDescription(),
				product.getListingDate().format(date_time)
		);
	}
}
