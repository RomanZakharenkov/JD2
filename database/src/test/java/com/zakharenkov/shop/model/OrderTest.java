package com.zakharenkov.shop.model;

import com.zakharenkov.shop.connection.Connection;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class OrderTest {

    @Test
    public void orderTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("testTEST@TEST.TEST")
                .password("TEST")
                .build();
        Order order = Order.builder()
                .status(OrderStatus.PROCESSED)
                .date(LocalDate.now())
                .build();

        @Cleanup Session session = Connection.getSession();
        session.beginTransaction();

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        order.setUser(userFind);
        Serializable saveOrderId = session.save(order);
        session.evict(order);
        Order orderFind = session.find(Order.class, saveOrderId);

        assertNotNull(orderFind);
        session.getTransaction().commit();
    }
}
