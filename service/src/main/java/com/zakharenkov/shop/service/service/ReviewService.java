package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.Review;
import com.zakharenkov.shop.database.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

}
