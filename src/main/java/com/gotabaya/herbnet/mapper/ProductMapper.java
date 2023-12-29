package com.gotabaya.herbnet.mapper;

import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;

public interface ProductMapper {
	ProductDto_short toDto_short(Product product);
	ProductDto_long toDto_long(Product product);
}