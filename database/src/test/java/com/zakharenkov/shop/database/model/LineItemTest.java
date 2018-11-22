//package com.zakharenkov.shop.database.model;
//
//import org.hibernate.Session;
//import org.junit.Test;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//
//import static org.junit.Assert.assertNotNull;
//
//public class LineItemTest extends BaseEntityTest{
//
//    @Test
//    public void lineItemTest() {
//        Product product = Product.builder()
//                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
//                .price(1000)
//                .build();
//        Category category = Category.builder()
//                .name("TESTT")
//                .build();
//        User user = User.builder()
//                .role(Role.USER)
//                .firstName("TEST")
//                .lastName("TEST")
//                .email("teTEST@TEST.TEST")
//                .password("TEST")
//                .build();
//        Order order = Order.builder()
//                .status(OrderStatus.PROCESSED)
//                .date(LocalDate.now())
//                .build();
//        LineItem lineItem = LineItem.builder()
//                .count(100)
//                .build();
//
//        Session session = getSessionFactory().getCurrentSession();
//        Serializable saveUserId = session.save(user);
//        session.evict(user);
//        User userFind = session.find(User.class, saveUserId);
//
//        order.setUser(userFind);
//        Serializable saveOrderId = session.save(order);
//        session.evict(order);
//        Order orderFind = session.find(Order.class, saveOrderId);
//
//        Serializable saveCategoryId = session.save(category);
//        session.evict(category);
//        Category categoryFind = session.find(Category.class, saveCategoryId);
//
//        product.setCategory(categoryFind);
//        Serializable saveProductId = session.save(product);
//        session.evict(product);
//        Product productFind = session.find(Product.class, saveProductId);
//
//        lineItem.setOrder(orderFind);
//        lineItem.setProduct(productFind);
//
//        Serializable saveLineItemId = session.save(lineItem);
//        session.evict(lineItem);
//        LineItem lineItemFind = session.find(LineItem.class, saveLineItemId);
//
//        assertNotNull(lineItemFind);
//    }
//}
