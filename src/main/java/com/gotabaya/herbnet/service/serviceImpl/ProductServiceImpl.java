package com.gotabaya.herbnet.service.serviceImpl;

import com.gotabaya.herbnet.mapper.ProductMapper;
import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.repository.ProductRepository;
import com.gotabaya.herbnet.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	final ProductRepository productRepository;
	final ProductMapper productMapper;
	@Override
	public List<ProductDto_short> list_short(String organ, String method) {
		return productRepository.findAll(organ, method).stream().map(productMapper::toDto_short).toList();
	}

	@Override
	public ProductDto_long getProduct_long(Long productId) {
		return productRepository.findById(productId).map(productMapper::toDto_long).orElseThrow(() -> new EntityNotFoundException("Product not found by Id: "+productId));
	}

	@Override
	public void addProduct(ProductDto_long product, String username) {
		if(product.productId() == null)
			saveProduct(product, username);
		else throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
	}

	@Override
	public void updateProduct(ProductDto_long product, String username) {
		Product p = productRepository.findById(product.productId()).orElseThrow(() -> new EntityNotFoundException("Product not found by Id: "+product.productId()));
		if(p.getUser().getUsername().equals(username))
			saveProduct(product, username);
		else throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
	}

	private void saveProduct(ProductDto_long product, String username){
		Product productEntity = productMapper.toEntity(product, username);
		productRepository.save(productEntity);
	}
}