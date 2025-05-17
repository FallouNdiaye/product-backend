package com.back.product.repository;

import com.back.product.model.Cart;
import com.back.product.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}