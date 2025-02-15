package com.gotabaya.herbnet.repository;

import com.gotabaya.herbnet.model.Product;
import com.gotabaya.herbnet.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends ListCrudRepository<Product, Long> {
	@Query("""
		SELECT p FROM Product p
		WHERE (COALESCE(:organ, '') = '' OR p.plantOrgan ILIKE :organ)
    AND (COALESCE(:method, '') = '' OR p.processingMethod ILIKE :method)
	""")
	List<Product> findAll(@Param("organ") String organ, @Param("method") String method);

	List<Product> findByUser(User user);
}
