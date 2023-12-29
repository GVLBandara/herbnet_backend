package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.Product;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
}
