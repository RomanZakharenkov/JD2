package dao;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class BaseDao<T extends BaseEntity> {

    private static SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Optional<T> findById(Class<T> tClass, Long id) {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        T entity = session.find(tClass, id);

        session.getTransaction().commit();
        return Optional.ofNullable(entity);
    }

    public List<T> findAll(Class<T> tClass) {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();


        session.getTransaction().commit();
        return null;
    }

    public Long save(BaseEntity baseEntity) {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        Long saveId = (Long) session.save(baseEntity);

        session.getTransaction().commit();
        return saveId;
    }

    public void update(BaseEntity baseEntity) {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        session.update(baseEntity);

        session.getTransaction().commit();
    }

    public void delete(BaseEntity baseEntity) {
        @Cleanup Session session = FACTORY.openSession();
        session.beginTransaction();

        session.delete(baseEntity);

        session.getTransaction().commit();
    }
}
