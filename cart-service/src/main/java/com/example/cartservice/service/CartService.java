package com.example.cartservice.service;

import com.example.cartservice.dto.CartEvent;
import com.example.cartservice.dto.ProductResponse;
import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.repository.CartItemRepository;
import com.example.cartservice.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);

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
        logger.info("API call received: create cart");
        return cartRepository.save(cart);
    }

    public CartItem addItemToCart(CartItem cartItem) {

        logger.info("API call received: add item to cart. cartId={}, productId={}, quantity={}",
                cartItem.getCartId(), cartItem.getProductId(), cartItem.getQuantity());
        if (cartItem.getQuantity() == null) {
            logger.error("Validation error: Quantity is null");
            throw new RuntimeException("Quantity cannot be null");
        }

        if (cartItem.getQuantity() <= 0) {
            logger.error("Validation error: Quantity must be greater than 0");
            throw new RuntimeException("Quantity must be greater than 0");
        }

        CompletableFuture<ProductResponse> productFuture =
                CompletableFuture.supplyAsync(() -> fetchProduct(cartItem.getProductId()));

        CompletableFuture<Boolean> stockValidationFuture =
                productFuture.thenApplyAsync(product ->
                        validateStock(product, cartItem.getQuantity())
                );

        ProductResponse product = productFuture.join();
        Boolean stockValid = stockValidationFuture.join();

        if (product == null || product.getId() == null) {
            logger.error("Error: Product does not exist. productId={}", cartItem.getProductId());
            throw new RuntimeException("Product does not exist");
        }

        if (!stockValid) {
            logger.error("Error: Insufficient stock. productId={}, requestedQuantity={}",
                    cartItem.getProductId(), cartItem.getQuantity());
            throw new RuntimeException("Insufficient stock");
        }

        CartItem savedItem = cartItemRepository.save(cartItem);

        logger.info("Cart item saved successfully. cartItemId={}", savedItem.getId());

        CartEvent cartEvent = new CartEvent(
                savedItem.getCartId(),
                savedItem.getProductId(),
                savedItem.getQuantity()
        );

        logger.info("Kafka event prepared. cartId={}, productId={}, quantity={}",
                cartEvent.getCartId(), cartEvent.getProductId(), cartEvent.getQuantity());

        kafkaProducerService.sendCartEvent(cartEvent);

        logger.info("Kafka event sent successfully to topic cart-events");

        return savedItem;
    }

    private ProductResponse fetchProduct(Long productId) {

        logger.info("Fetching product asynchronously. productId={}, thread={}",
                productId, Thread.currentThread().getName());

        return webClient.get()
                .uri("/products/" + productId)
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
    }

    private Boolean validateStock(ProductResponse product, Integer quantity) {

        logger.info("Validating stock asynchronously. thread={}", Thread.currentThread().getName());

        if (product == null || product.getId() == null) {
            logger.error("Stock validation failed: product is null");
            return false;
        }

        boolean stockAvailable = product.getStock() >= quantity;

        logger.info("Stock validation result. productId={}, availableStock={}, requestedQuantity={}, valid={}",
                product.getId(), product.getStock(), quantity, stockAvailable);

        return stockAvailable;
    }

    public List<CartItem> getAllCartItems() {
        logger.info("API call received: get all cart items");
        return cartItemRepository.findAll();
    }
}