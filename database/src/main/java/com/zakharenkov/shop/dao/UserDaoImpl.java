package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.User;

public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

    private static final UserDaoImpl INSTANCE = new UserDaoImpl();

    private UserDaoImpl() {
        super(User.class);
    }

    public static UserDaoImpl getInstance() {
        return INSTANCE;
    }
}
