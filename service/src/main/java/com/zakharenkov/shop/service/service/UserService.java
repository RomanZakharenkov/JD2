package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }
}
