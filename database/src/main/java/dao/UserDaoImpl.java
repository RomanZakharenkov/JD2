package dao;

import model.User;

public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl(User.class);

    // TODO: реализация кастомных методов на юзеров

    public UserDaoImpl(Class<User> clazz) {
        super(clazz);
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
}
