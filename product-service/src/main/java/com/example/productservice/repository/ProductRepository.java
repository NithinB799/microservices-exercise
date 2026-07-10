package com.example.productservice.repository;

import com.example.productservice.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;//added in 1G
import org.springframework.data.repository.query.Param;//added in 1G

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE price > :price", nativeQuery = true)
    List<Product> findProductsAbovePrice(@Param("price") Double price);

}
