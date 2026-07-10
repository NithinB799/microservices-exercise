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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

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
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());

        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);
    }
    public Page<Product> getProductsWithPaginationAndSorting(
            int page,
            int size,
            String sortBy,
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable);
    }
    public Page<Product> searchProducts(
            String name,
            Double minPrice,
            Double maxPrice,
            Integer minStock,
            int page,
            int size,
            String sortBy,
            String direction) {

        Specification<Product> specification =
                (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (name != null && !name.isBlank()) {
            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("name")),
                                    "%" + name.toLowerCase() + "%"
                            )
            );
        }

        if (minPrice != null) {
            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("price"),
                                    minPrice
                            )
            );
        }

        if (maxPrice != null) {
            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("price"),
                                    maxPrice
                            )
            );
        }

        if (minStock != null) {
            specification = specification.and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("stock"),
                                    minStock
                            )
            );
        }

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(specification, pageable);
    }

}

