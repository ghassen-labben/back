package com.example.project.controllers;

import com.example.project.models.Product;
import com.example.project.models.Review;
import com.example.project.services.ProductService;
import com.example.project.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@CrossOrigin("http://localhost:4200")
public class ReviewController {
    private final ReviewService reviewService;
    private final ProductService productService;
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }
    @GetMapping("/latest")
    public List<Review> getLatest(){
        return reviewService.getLatestReviews(10);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        if (review != null) {
            return ResponseEntity.ok(review);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/{productId}")
    public ResponseEntity<Review> addReview(
            @PathVariable Long productId,
            @RequestBody Review review) {
        Product product=productService.getProductById(productId);
        review.setProduct(product);
        Review addedReview = reviewService.addReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedReview);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Review updatedReview = reviewService.updateReview(id, review);
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}
