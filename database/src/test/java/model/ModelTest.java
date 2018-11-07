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

        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

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

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        order.setUser(userFind);
        Serializable saveOrderId = session.save(order);
        session.evict(order);
        Order orderFind = session.find(Order.class, saveOrderId);

        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

        lineItem.setOrder(orderFind);
        lineItem.setProduct(productFind);

        Serializable saveLineItemId = session.save(lineItem);
        session.evict(lineItem);
        LineItem lineItemFind = session.find(LineItem.class, saveLineItemId);

        assertNotNull(lineItemFind);
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

        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        review.setProduct(productFind);
        review.setUser(userFind);

        Serializable saveReviewId = session.save(review);
        session.evict(review);
        Review reviewFind = session.find(Review.class, saveReviewId);

        assertNotNull(reviewFind);
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

        Serializable saveCategoryId = session.save(category);
        session.evict(category);
        Category categoryFind = session.find(Category.class, saveCategoryId);

        product.setCategory(categoryFind);
        Serializable saveProductId = session.save(product);
        session.evict(product);
        Product productFind = session.find(Product.class, saveProductId);

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

        Serializable saveUserId = session.save(user);
        session.evict(user);
        User userFind = session.find(User.class, saveUserId);

        assertNotNull(userFind);
        session.getTransaction().commit();
    }
}
