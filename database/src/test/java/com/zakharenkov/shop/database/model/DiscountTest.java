package com.zakharenkov.shop.database.model;

import org.hibernate.Session;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class DiscountTest extends BaseEntityTest {

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

        Session session = getSessionFactory().getCurrentSession();
        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        sale.setUser(userFind);
        Serializable saveSaleId = session.save(sale);
        session.evict(sale);
        Sale saleFind = session.find(Sale.class, saveSaleId);

        assertNotNull(saleFind);
    }
}
