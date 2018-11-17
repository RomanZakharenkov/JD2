package com.zakharenkov.shop.database.model;

import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class ProductTest extends BaseEntityTest {

    @Test
    public void productTest() {
        Product product = Product.builder()
                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
                .price(1000)
                .build();
        Category category = Category.builder()
                .name("TEST")
                .build();

        Session session = getSessionFactory().getCurrentSession();
        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

        assertNotNull(productFind);
    }
}
