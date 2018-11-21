package com.zakharenkov.shop.database.model;

import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class UserTest extends BaseEntityTest {

    @Test
    public void userTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST3@TEST.TEST")
                .password("TEST")
                .build();

        Session session = getSessionFactory().getCurrentSession();
        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        assertNotNull(userFind);
    }
}
