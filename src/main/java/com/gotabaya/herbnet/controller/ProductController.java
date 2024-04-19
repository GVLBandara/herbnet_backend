package com.gotabaya.herbnet.controller;

import com.gotabaya.herbnet.model.dto.ProductDto_long;
import com.gotabaya.herbnet.model.dto.ProductDto_short;
import com.gotabaya.herbnet.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	final ProductService productService;

	@GetMapping("")
	List<ProductDto_short> MyProducts(Principal principal){
		return productService.fromUser(principal.getName());
	}

	@GetMapping("/search")
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

	@PostMapping("/new")
	ResponseEntity<String> newProduct(@RequestBody ProductDto_long product, Principal principal){
		try {
			productService.addProduct(product, principal.getName());
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}catch (HttpServerErrorException e){
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	@PutMapping("/update")
	ResponseEntity<String> updateProduct(@RequestBody ProductDto_long product, Principal principal){
		try {
			productService.updateProduct(product, principal.getName());
			return ResponseEntity.ok().build();
		}catch (HttpServerErrorException e){
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}

	@DeleteMapping("/delete/{productId}")
	ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId, Principal principal){
		try{
			productService.deleteProduct(productId, principal.getName());
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}catch (HttpServerErrorException e){
			return ResponseEntity.status(e.getStatusCode()).build();
		}
	}
}
