import lombok.Cleanup;
import model.Category;
import model.Product;
import model.ProductDetail;
import model.Review;
import model.Storage;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {


        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = session.find(User.class, 3L);
        Product product = session.find(Product.class, 3L);
//        Storage storage = Storage.builder()
//                .product(product)
//                .id(product.getId())
//                .count(10)
//                .build();

        Review review = Review.builder()
                .date(LocalDate.now())
                .text("TEST")
                .product(product)
                .user(user)
                .build();

        session.save(review);
        session.getTransaction().commit();
    }
}
