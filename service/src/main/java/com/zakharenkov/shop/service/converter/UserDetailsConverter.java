package com.zakharenkov.shop.service.converter;

import com.zakharenkov.shop.database.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsConverter implements Converter<User, UserDetails> {

    @Override
    public UserDetails convert(User user) {
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password("{noop}" + user.getPassword())
                .authorities(user.getRole().getName())
                .build();
    }
}
