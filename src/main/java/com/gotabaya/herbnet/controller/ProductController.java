package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	final ProductService productService;

	@GetMapping("")
	List<ProductDto_short> findAll(
			@RequestParam(value = "organ", required = false) String organ,
			@RequestParam(value = "method", required = false) String method
	){
		return productService.list_short(organ, method);
	}

	@GetMapping("/{productId}")
	ProductDto_long getProduct(@PathVariable Long productId){
		return productService.getProduct_long(productId);
	}

	@PostMapping("")
	void newProduct(@RequestBody ProductDto_long product, Principal principal){
		productService.addProduct(product, principal.getName());
	}
}
