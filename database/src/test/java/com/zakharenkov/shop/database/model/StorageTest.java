package com.zakharenkov.shop.database.model;

import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class StorageTest extends BaseEntityTest{

    @Test
    public void storageTest() {
        Product product = Product.builder()
                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
                .price(1000)
                .build();
        Category category = Category.builder()
                .name("TEST7")
                .build();
        Storage storage = Storage.builder()
                .count(100)
                .build();

        Session session = getSessionFactory().getCurrentSession();
        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

        storage.setProduct(productFind);
        storage.setId(productFind.getId());

        Serializable saveStorageId = session.save(storage);
        session.evict(storage);
        Storage storageFind = session.find(Storage.class, saveStorageId);

        assertNotNull(storageFind);
    }

}
