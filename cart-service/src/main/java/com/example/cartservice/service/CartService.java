package com.example.cartservice.service;

import com.example.cartservice.dto.CartEvent;
import com.example.cartservice.dto.ProductResponse;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartItemRepository;
import com.example.cartservice.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final WebClient webClient;
    private final KafkaProducerService kafkaProducerService;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       KafkaProducerService kafkaProducerService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.kafkaProducerService = kafkaProducerService;
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public CartItem addItemToCart(CartItem cartItem) {

        ProductResponse product = webClient.get()
                .uri("/products/" + cartItem.getProductId())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();

        if (product == null || product.getId() == null) {
            throw new RuntimeException("Product does not exist");
        }

        if (product.getStock() < cartItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        CartItem savedItem = cartItemRepository.save(cartItem);

        CartEvent cartEvent = new CartEvent(
                savedItem.getCartId(),
                savedItem.getProductId(),
                savedItem.getQuantity()
        );

        kafkaProducerService.sendCartEvent(cartEvent);

        return savedItem;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}