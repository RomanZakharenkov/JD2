package com.zakharenkov.shop.service;

import com.zakharenkov.shop.dao.UserDaoImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.zakharenkov.shop.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User findUserById(Long id) {
        return UserDaoImpl.getInstance().findById(id);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
