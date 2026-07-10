package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public boolean isStockAvailable(Long productId, Integer quantity) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            return false;
        }

        return product.get().getStock() >= quantity;
    }
    public Page<Product> getProductsWithPagingAndSorting(int pageNumber,
                                                         int pageSize,
                                                         String sortField) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortField));
        return productRepository.findAll(pageable);
    }

    public List<Product> filterProductsByStock(Integer minimumStock) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getStock() >= minimumStock)
                .toList();
    }

    public List<String> getProductNames() {
        return productRepository.findAll()
                .stream()
                .map(Product::getName)
                .toList();
    }

    public List<Product> getProductsAbovePrice(Double price) {
        return productRepository.findProductsAbovePrice(price);//added in 1G
    }

}

