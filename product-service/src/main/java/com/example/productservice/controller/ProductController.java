package com.example.productservice.controller;

import com.example.productservice.model.Product;
import com.example.productservice.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;

import org.springframework.data.domain.Page;//added in 1F.

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

}