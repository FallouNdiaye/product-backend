package com.back.product.controller;

import com.back.product.model.User;
import com.back.product.model.Wishlist;
import com.back.product.service.WishlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<Wishlist> getWishlist(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(wishlistService.getWishlist(user));
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Wishlist> addToWishlist(@PathVariable Long productId,
                                                  @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(wishlistService.addToWishlist(user, productId));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Wishlist> removeFromWishlist(@PathVariable Long productId,
                                                       @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(wishlistService.removeFromWishlist(user, productId));
    }
}