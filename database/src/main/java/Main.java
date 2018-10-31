import lombok.Cleanup;
import model.Category;
import model.Coupon;
import model.Order;
import model.OrderStatus;
import model.Product;
import model.ProductDetail;
import model.Review;
import model.Role;
import model.Sale;
import model.SaleStatus;
import model.Storage;
import model.TypeSale;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.time.LocalDate;

public class Main {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
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
        session.getTransaction().commit();
    }
}
