package model;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.Serializable;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;

public class ModelTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void closeFactory() {
        FACTORY.close();
    }

    @Test
    public void categoryTest() {
        Category category = Category.builder()
                .name("TEST2")
                .build();
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Serializable serializable = session.save(category);

        session.evict(category);

        Category categoryFind = session.find(Category.class, serializable);
        assertNotNull(categoryFind);
        session.getTransaction().commit();
    }

    @Test
    public void lineItemTest() {
        Product product = Product.builder()
                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
                .price(1000)
                .build();
        Category category = Category.builder()
                .name("TESTT")
                .build();
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("teTEST@TEST.TEST")
                .password("TEST")
                .build();
        Order order = Order.builder()
                .status(OrderStatus.PROCESSED)
                .date(LocalDate.now())
                .build();
        LineItem lineItem = LineItem.builder()
                .count(100)
                .build();
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable serializable = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, serializable);

        order.setUser(userFind);
        Serializable serializable1 = session.save(order);
        session.evict(order);
        Order orderFind = session.find(Order.class, serializable1);

        Serializable saveID = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveID);

        product.setCategory(categoryFind);
        Serializable save = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, save);

        lineItem.setOrder(orderFind);
        lineItem.setProduct(productFind);

        Serializable save1 = session.save(lineItem);
        session.evict(lineItem);
        LineItem lineItem1 = session.find(LineItem.class, save1);

        assertNotNull(lineItem1);
        session.getTransaction().commit();
    }

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
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable save1 = session.save(category);
        session.evict(category);
        Category category1 = session.find(Category.class, save1);

        product.setCategory(category1);
        Serializable save2 = session.save(product);
        session.evict(product);
        Product product1 = session.find(Product.class, save2);

        Serializable save3 = session.save(user);
        session.evict(user);
        User user1 = session.find(User.class, save3);

        review.setProduct(product1);
        review.setUser(user1);

        Serializable saveId = session.save(review);
        session.evict(review);
        Review review1 = session.find(Review.class, saveId);

        assertNotNull(review1);
        session.getTransaction().commit();
    }

    @Test
    public void couponTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST@TEST.TEST")
                .password("TEST")
                .build();
        Sale sale = new Coupon(null, LocalDate.now(), LocalDate.now(), TypeSale.COUPON, SaleStatus.ACTIVE, "", 100);
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable save = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, save);
        sale.setUser(userFind);

        Serializable save1 = session.save(sale);
        session.evict(sale);
        Sale sale1 = session.find(Sale.class, save1);

        assertNotNull(sale1);
        session.getTransaction().commit();
    }

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
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable save = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, save);
        sale.setUser(userFind);

        Serializable save1 = session.save(sale);
        session.evict(sale);
        Sale sale1 = session.find(Sale.class, save1);

        assertNotNull(sale1);
        session.getTransaction().commit();
    }

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

        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable save = session.save(category);
        session.evict(category);
        Category category1 = session.find(Category.class, save);

        product.setCategory(category1);
        Serializable save1 = session.save(product);
        session.evict(product);
        Product product1 = session.find(Product.class, save1);

        storage.setProduct(product1);
        storage.setId(product1.getId());

        Serializable save2 = session.save(storage);
        session.evict(storage);
        Storage storage1 = session.find(Storage.class, save2);

        assertNotNull(storage1);
        session.getTransaction().commit();
    }

    @Test
    public void productTest() {
        Product product = Product.builder()
                .productDetail(ProductDetail.of("TEST", "TEST", "TEST", "TEST"))
                .price(1000)
                .build();
        Category category = Category.builder()
                .name("TEST")
                .build();
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable saveID = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveID);

        product.setCategory(categoryFind);
        Serializable serializable = session.save(product);

        session.evict(product);

        Product productFind = session.find(Product.class, serializable);
        assertNotNull(productFind);
        session.getTransaction().commit();
    }

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
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Serializable serializable = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, serializable);

        order.setUser(userFind);
        Serializable serializable1 = session.save(order);

        session.evict(order);

        Order orderFind = session.find(Order.class, serializable1);
        assertNotNull(orderFind);
        session.getTransaction().commit();
    }

    @Test
    public void userTest() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("TEST")
                .lastName("TEST")
                .email("TEST3@TEST.TEST")
                .password("TEST")
                .build();
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();
        Serializable serializable = session.save(user);

        session.evict(user);

        User userFind = session.find(User.class, serializable);
        assertNotNull(userFind);
        session.getTransaction().commit();
    }

}
