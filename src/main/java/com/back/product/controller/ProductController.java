package com.back.product.controller;

import com.back.product.model.Product;
import com.back.product.model.User;
import com.back.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product,
                                                 @AuthenticationPrincipal User user) {
        if (!"admin@admin.com".equals(user.getEmail())) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody Product product,
                                                 @AuthenticationPrincipal User user) {
        if (!"admin@admin.com".equals(user.getEmail())) {
            return ResponseEntity.status(403).build();
        }
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id,
                                              @AuthenticationPrincipal User user) {
        if (!"admin@admin.com".equals(user.getEmail())) {
            return ResponseEntity.status(403).build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
