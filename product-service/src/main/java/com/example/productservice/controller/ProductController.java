package com.example.productservice.controller;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.data.domain.Page;//added in 1F.
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/stock")
    public boolean checkStock(@PathVariable Long id,
                              @RequestParam Integer quantity) {
        return productService.isStockAvailable(id, quantity);
    }
    @GetMapping("/paged")
    public Page<Product> getProductsWithPagingAndSorting(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String sortField) {

        return productService.getProductsWithPagingAndSorting(pageNumber, pageSize, sortField);
    }

    @GetMapping("/filter")
    public List<Product> filterProductsByStock(@RequestParam Integer minimumStock) {
        return productService.filterProductsByStock(minimumStock);
    }

    @GetMapping("/names")
    public List<String> getProductNames() {
        return productService.getProductNames();
    }

    @GetMapping("/price")
    public List<Product> getProductsAbovePrice(@RequestParam Double price) {
        return productService.getProductsAbovePrice(price);
    }
    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody Product product) {

        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/page")
    public Page<Product> getProductsWithPaginationAndSorting(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.getProductsWithPaginationAndSorting(
                page,
                size,
                sortBy,
                direction
        );
    }
    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minStock,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return productService.searchProducts(
                name,
                minPrice,
                maxPrice,
                minStock,
                page,
                size,
                sortBy,
                direction
        );
    }

}