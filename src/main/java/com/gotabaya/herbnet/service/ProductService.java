package com.gotabaya.herbnet.service;

import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;

import java.util.List;

public interface ProductService {
	List<ProductDto_short> fromUser(String username);
	List<ProductDto_short> list_short(String organ, String method);
	ProductDto_long getProduct_long(Long productId);
	void addProduct(ProductDto_long product, String username);
	void updateProduct(ProductDto_long product, String username);
	void deleteProduct(Long productId, String username);
}
