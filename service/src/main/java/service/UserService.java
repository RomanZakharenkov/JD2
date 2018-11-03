package service;

import dao.UserDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

//    public User findUserById(Long id) {
//        return UserDaoImpl.getInstance().findById(User.class, id).get();
//    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
