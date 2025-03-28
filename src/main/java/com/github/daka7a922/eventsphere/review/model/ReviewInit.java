package com.github.daka7a922.eventsphere.review.model;

import com.github.daka7a922.eventsphere.review.repository.ReviewRepository;
import com.github.daka7a922.eventsphere.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ReviewInit implements CommandLineRunner {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewService reviewService;

    @Override
    public void run(String... args) throws Exception {


        if (reviewRepository.count() == 0) {

            List<Review> reviews = Arrays.asList(
                    reviewService.createReview(
                            "Gosho ot pochivka",
                            "5",
                            "AIDEEEE.",
                            LocalDateTime.now().minusDays(10)
                    ),
                    reviewService.createReview(
                            "Pesho",
                            "4",
                            "Great event!",
                            LocalDateTime.now().minusDays(15)
                    ),
                    reviewService.createReview(
                            "Michael J.",
                            "5",
                            "Exceeded all my expectations!",
                            LocalDateTime.now().minusDays(5)
                    ),
                    reviewService.createReview(
                            "Sarah WW",
                            "3",
                            "Decent event, but some technical issues with the audio system were distracting.",
                            LocalDateTime.now().minusDays(20)
                    ),
                    reviewService.createReview(
                            "David Brown",
                            "4",
                            "Professionally organized with high-quality content. Would definitely attend again.",
                            LocalDateTime.now().minusDays(12)
                    ),
                    reviewService.createReview(
                            "Lisa Martinez",
                            "5",
                            "An outstanding event!",
                            LocalDateTime.now().minusDays(8)
                    )
            );

        }
    }
}
