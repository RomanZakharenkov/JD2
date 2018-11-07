package com.zakharenkov.shop.model;

import com.zakharenkov.shop.connection.Connection;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ReviewTest {

    @Test
    public void reviewTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST5@TEST.TEST")
                .password("TEST")
                .build();
        Product product = Product.builder()
                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
                .price(1000)
                .build();
        Category category = Category.builder()
                .name("TEST6")
                .build();
        Review review = Review.builder()
                .text("TEST")
                .date(LocalDate.now())
                .build();

        @Cleanup Session session = Connection.getSession();
        session.beginTransaction();

        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        review.setProduct(productFind);
        review.setUser(userFind);

        Serializable saveReviewId = session.save(review);
        session.evict(review);
        Review reviewFind = session.find(Review.class, saveReviewId);

        assertNotNull(reviewFind);
        session.getTransaction().commit();
    }
}
