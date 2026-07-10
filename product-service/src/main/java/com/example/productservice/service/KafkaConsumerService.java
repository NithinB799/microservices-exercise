package com.example.productservice.service;

import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KafkaConsumerService {

    private final ProductRepository productRepository;

    public KafkaConsumerService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @KafkaListener(topics = "cart-events", groupId = "product-group")
    public void consumeCartEvent(String message) {

        System.out.println("Kafka event received: " + message);

        Long productId = extractLongValue(message, "productId");
        Integer quantity = extractIntegerValue(message, "quantity");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        int updatedStock = product.getStock() - quantity;

        if (updatedStock < 0) {
            System.out.println("Stock cannot be reduced. Insufficient stock.");
            return;
        }

        product.setStock(updatedStock);

        productRepository.save(product);

        System.out.println("Product stock updated. Product ID: "
                + product.getId()
                + ", New Stock: "
                + product.getStock());
    }

    private Long extractLongValue(String message, String fieldName) {
        Pattern pattern = Pattern.compile("\"" + fieldName + "\"\\s*:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }

        throw new RuntimeException("Missing field: " + fieldName);
    }

    private Integer extractIntegerValue(String message, String fieldName) {
        Pattern pattern = Pattern.compile("\"" + fieldName + "\"\\s*:\\s*(\\d+)");
        Matcher matcher = pattern.matcher(message);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }

        throw new RuntimeException("Missing field: " + fieldName);
    }
}