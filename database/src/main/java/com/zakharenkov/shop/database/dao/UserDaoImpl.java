package com.zakharenkov.shop.database.dao;

import com.zakharenkov.shop.database.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<Long, User> implements UserDao {

}
