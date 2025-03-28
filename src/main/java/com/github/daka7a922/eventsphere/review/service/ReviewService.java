package com.github.daka7a922.eventsphere.review.service;

import com.github.daka7a922.eventsphere.review.model.Review;
import com.github.daka7a922.eventsphere.review.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(String name, String rating, String comment, LocalDateTime reviewDate) {
        Review review = Review.builder()
                .name(name)
                .rating(rating)
                .comment(comment)
                .reviewDate(reviewDate)
                .build();

        return reviewRepository.save(review);
    }
}
