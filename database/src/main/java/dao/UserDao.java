package dao;

import lombok.AccessLevel;
import lombok.Cleanup;
import lombok.NoArgsConstructor;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String GET_BY_ID =
            "SELECT " +
                    "u.id AS id, " +
                    "u.role AS role, " +
                    "u.first_name AS firstName, " +
                    "u.last_name AS lastName, " +
                    "u.email AS email, " +
                    "u.password AS password, " +
                    "u.phone_number AS number " +
                    "FROM shop.\"user\" u " +
                    "WHERE u.id = ? ;";

    public void save(User user) {
        @Cleanup SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    public Optional<User> getById(Long id) {
        User user = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        user = session.get(User.class, id);
        System.out.println("dao - " + user);
        session.getTransaction().commit();

        return Optional.ofNullable(user);
    }
//    public Optional<User> getById(Integer id) {
//        User user = null;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                user = User.builder()
//                        .id(resultSet.getInt("id"))
//                        .role(Role.getByName(resultSet.getString("role")))
//                        .firstName(resultSet.getString("firstName"))
//                        .lastName(resultSet.getString("lastName"))
//                        .email(resultSet.getString("email"))
//                        .password(resultSet.getString("password"))
//                        .number(resultSet.getString("number"))
//                        .build();
//            }
//        } catch (SQLException e) {
//            throw new DaoException(e);
//        }
//        return Optional.ofNullable(user);
//    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
