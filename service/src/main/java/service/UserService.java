package service;

import dao.UserDao;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User getUser(Long id) {
        return UserDao.getInstance().getById(id).get();
    }

//    public void save(User user) {
//        UserDao.getInstance().save(user);
//    }

    public static UserService getInstance() {
        return INSTANCE;
    }

}
