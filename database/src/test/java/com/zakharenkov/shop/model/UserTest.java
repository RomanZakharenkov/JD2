package com.zakharenkov.shop.model;

import com.zakharenkov.shop.connection.Connection;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class UserTest {

    @Test
    public void userTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST3@TEST.TEST")
                .password("TEST")
                .build();

        @Cleanup Session session = Connection.getSession();
        session.beginTransaction();

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        assertNotNull(userFind);
        session.getTransaction().commit();
    }
}
