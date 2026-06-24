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
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<ProductResponse> productFuture =
                CompletableFuture.supplyAsync(() -> fetchProduct(cartItem.getProductId()));

        CompletableFuture<Boolean> stockValidationFuture =
                productFuture.thenApplyAsync(product ->
                        validateStock(product, cartItem.getQuantity())
                );

        ProductResponse product = productFuture.join();
        Boolean stockValid = stockValidationFuture.join();

        if (product == null || product.getId() == null) {
            throw new RuntimeException("Product does not exist");
        }

        if (!stockValid) {
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

    private ProductResponse fetchProduct(Long productId) {
        System.out.println("Fetching product on thread: " + Thread.currentThread().getName());

        return webClient.get()
                .uri("/products/" + productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

    private Boolean validateStock(ProductResponse product, Integer quantity) {
        System.out.println("Validating stock on thread: " + Thread.currentThread().getName());

        if (product == null || product.getId() == null) {
            return false;
        }

        return product.getStock() >= quantity;
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }
}