package com.zakharenkov.shop.model;

import com.zakharenkov.shop.connection.Connection;
import lombok.Cleanup;
import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class DiscountTest {

    @Test
    public void discountTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST2@TEST.TEST")
                .password("TEST")
                .build();
        Sale sale = new Discount(null, LocalDate.now(), LocalDate.now(), TypeSale.DISCOUNT, SaleStatus.ACTIVE, "", 100);

        @Cleanup Session session = Connection.getSession();
        session.beginTransaction();

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        sale.setUser(userFind);
        Serializable saveSaleId = session.save(sale);
        session.evict(sale);
        Sale saleFind = session.find(Sale.class, saveSaleId);

        assertNotNull(saleFind);
        session.getTransaction().commit();
    }
}
