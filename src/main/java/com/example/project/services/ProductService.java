package com.example.project.services;

import com.example.project.models.Product;
import com.example.project.models.Review;
import com.example.project.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
            existingProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    public List<Product> getTopRatedProducts() {
        List<Product> products = this.getAllProducts();
        products.sort(Comparator.comparingDouble((Product product) -> product.getReviews().stream()
                        .mapToDouble(Review::getRating)
                        .average()
                        .orElse(0.0))
                .reversed());
        return products;
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
