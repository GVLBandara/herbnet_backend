package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	final ProductService productService;

	@GetMapping("")
	List<ProductDto_short> findAll(){
		return productService.list_short();
	}

	@GetMapping("/{productId}")
	ProductDto_long getProduct(@PathVariable Long productId){
		return productService.getProduct_long(productId);
	}
}
