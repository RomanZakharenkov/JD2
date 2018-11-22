package com.zakharenkov.shop.database.repository;

import com.zakharenkov.shop.database.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
