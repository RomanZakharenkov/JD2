import lombok.Cleanup;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {

        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = User.of(Role.USER, "Дмитрий", "Дмитриев", "dmitriy@mail.ru", "1234", "375292002121");
        System.out.println(user);
        session.save(user);
        session.getTransaction().commit();
    }
}
