package com.bella.movies.controllers;

import com.bella.movies.models.Review;
import com.bella.movies.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> saveReview(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<>(reviewService.saveReview(
                payload.get("reviewBody"),
                payload.get("imdbId")
        ), HttpStatus.CREATED);
    }
}
