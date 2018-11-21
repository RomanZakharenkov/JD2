package com.zakharenkov.shop.database.model;

import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class CategoryTest extends BaseEntityTest {

    @Test
    public void categoryTest() {
        Category category = Category.builder()
                .name("TEST2")
                .build();

        Session session = getSessionFactory().getCurrentSession();
        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        assertNotNull(categoryFind);
    }
}
