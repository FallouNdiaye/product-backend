package com.back.product.service;

import com.back.product.model.Product;
import com.back.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        product.setCreatedAt(System.currentTimeMillis());
        product.setUpdatedAt(System.currentTimeMillis());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setCode(updatedProduct.getCode());
                    product.setName(updatedProduct.getName());
                    product.setDescription(updatedProduct.getDescription());
                    product.setImage(updatedProduct.getImage());
                    product.setCategory(updatedProduct.getCategory());
                    product.setPrice(updatedProduct.getPrice());
                    product.setQuantity(updatedProduct.getQuantity());
                    product.setInternalReference(updatedProduct.getInternalReference());
                    product.setShellId(updatedProduct.getShellId());
                    product.setInventoryStatus(updatedProduct.getInventoryStatus());
                    product.setRating(updatedProduct.getRating());
                    product.setUpdatedAt(System.currentTimeMillis());
                    return productRepository.save(product);
                });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}