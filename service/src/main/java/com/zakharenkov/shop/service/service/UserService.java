package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.service.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    User saveUser(UserRegistrationDto user);

    User update(User user);
}
