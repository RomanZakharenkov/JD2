package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
