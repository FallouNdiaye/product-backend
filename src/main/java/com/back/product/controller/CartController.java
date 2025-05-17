package com.back.product.controller;

import com.back.product.model.Cart;
import com.back.product.model.User;
import com.back.product.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Cart> addToCart(@PathVariable Long productId,
                                          @RequestParam Integer quantity,
                                          @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.addToCart(user, productId, quantity));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Cart> removeFromCart(@PathVariable Long productId,
                                               @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.removeFromCart(user, productId));
    }
}