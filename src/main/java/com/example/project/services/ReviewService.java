package com.example.project.services;

import com.example.project.exceptions.ReviewNotFoundException;
import com.example.project.models.Review;
import com.example.project.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + id + " not found"));
    }

    public Review addReview(Review review) {

        return reviewRepository.save(review);
    }

    public List<Review> getLatestReviews(int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit);
        return reviewRepository.findLatestReviews(pageRequest);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with ID " + id + " not found"));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setReviewSnippet(updatedReview.getReviewSnippet());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new ReviewNotFoundException("Review with ID " + id + " not found");
        }
        reviewRepository.deleteById(id);
    }
}
