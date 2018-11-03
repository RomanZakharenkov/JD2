package dao;

import model.Review;

public class ReviewDaoImpl extends BaseDaoImpl<Long, Review> implements ReviewDao {

    private static final ReviewDao INSTANCE = new ReviewDaoImpl(Review.class);

    // TODO: реализация кастомных методов на отзывы

    public ReviewDaoImpl(Class<Review> clazz) {
        super(clazz);
    }

    public static ReviewDao getInstance() {
        return INSTANCE;
    }
}
