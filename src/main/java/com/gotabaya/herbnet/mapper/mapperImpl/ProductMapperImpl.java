package com.gotabaya.herbnet.mapper.mapperImpl;

import com.gotabaya.herbnet.mapper.ProductMapper;
import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.Species;
import com.gotabaya.herbnet.model.User;
import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.repository.SpeciesRepository;
import com.gotabaya.herbnet.repository.UserRepository;
import com.gotabaya.herbnet.security.exception.UserNotFoundException;
import com.gotabaya.herbnet.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {
	final UserProfileService userProfileService;
	final UserRepository userRepository;
	final SpeciesRepository speciesRepository;
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

	@Override
	public Product toEntity(ProductDto_long product, String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User doesn't exist by username " + username));
		Species species = speciesRepository.findSpeciesByCommonName(product.plantName()).orElseThrow(() -> new UserNotFoundException("Species doesn't exist by name " + product.plantName()));

		return new Product(
				null,
				user,
				species,
				product.plantOrgan(),
				product.plantName(),
				product.processingMethod(),
				product.description(),
				LocalDateTime.now()
		);
	}
}
