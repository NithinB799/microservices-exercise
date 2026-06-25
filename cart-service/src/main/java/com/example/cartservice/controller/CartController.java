package com.example.cartservice.controller;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.entity.CartItem;
import com.example.cartservice.service.CartService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @PostMapping("/items")
    public CartItem addItemToCart(@Valid @RequestBody CartItem cartItem) {
        return cartService.addItemToCart(cartItem);
    }

    @GetMapping("/items")
    public List<CartItem> getAllCartItems() {
        return cartService.getAllCartItems();
    }

}