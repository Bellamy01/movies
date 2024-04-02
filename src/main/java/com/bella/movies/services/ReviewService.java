package com.bella.movies.services;

import com.bella.movies.models.Movie;
import com.bella.movies.models.Review;
import com.bella.movies.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Review saveReview(String ReviewBody, String imdbId) {
        Review savedReview = reviewRepository.save(new Review(ReviewBody));

        mongoTemplate.update(Movie.class)
            .matching(Criteria.where("imdbId").is(imdbId))
            .apply(new Update().push("reviewIds").value(savedReview.getId()))
            .first();

        return savedReview;
    }
}
