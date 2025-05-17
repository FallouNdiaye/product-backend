package com.back.product.service;


import com.back.product.model.Cart;
import com.back.product.model.CartItem;
import com.back.product.model.Product;
import com.back.product.model.User;
import com.back.product.repository.CartRepository;
import com.back.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public Cart getCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    public Cart addToCart(User user, Long productId, Integer quantity) {
        Cart cart = getCart(user);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(quantity);
            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    public Cart removeFromCart(User user, Long productId) {
        Cart cart = getCart(user);
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        return cartRepository.save(cart);
    }
}