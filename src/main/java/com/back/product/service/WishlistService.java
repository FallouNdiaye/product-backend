package com.back.product.service;

import com.back.product.model.Wishlist;
import com.back.product.model.WishlistItem;
import com.back.product.model.Product;
import com.back.product.model.User;
import com.back.product.repository.WishlistRepository;
import com.back.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    public Wishlist getWishlist(User user) {
        return wishlistRepository.findByUser(user)
                .orElseGet(() -> {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setUser(user);
                    return wishlistRepository.save(wishlist);
                });
    }

    public Wishlist addToWishlist(User user, Long productId) {
        Wishlist wishlist = getWishlist(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        if (wishlist.getItems().stream().noneMatch(item -> item.getProduct().getId().equals(productId))) {
            WishlistItem item = new WishlistItem();
            item.setWishlist(wishlist);
            item.setProduct(product);
            wishlist.getItems().add(item);
        }

        return wishlistRepository.save(wishlist);
    }

    public Wishlist removeFromWishlist(User user, Long productId) {
        Wishlist wishlist = getWishlist(user);
        wishlist.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return wishlistRepository.save(wishlist);
    }
}