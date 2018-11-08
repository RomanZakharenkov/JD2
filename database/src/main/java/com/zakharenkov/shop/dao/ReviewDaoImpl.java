package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Review;

public class ReviewDaoImpl extends BaseDaoImpl<Long, Review> implements ReviewDao {

    private static final ReviewDao INSTANCE = new ReviewDaoImpl();

    private ReviewDaoImpl() {
        super(Review.class);
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
