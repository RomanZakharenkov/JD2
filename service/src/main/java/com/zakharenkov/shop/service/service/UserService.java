package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.dao.UserDaoImpl;
import com.zakharenkov.shop.database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public Optional<User> findUserById(Long id) {
        Optional<User> user = userDao.findById(id);
        return user;
    }
}
