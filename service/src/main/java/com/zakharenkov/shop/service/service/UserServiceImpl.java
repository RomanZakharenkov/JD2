package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.User;
import com.zakharenkov.shop.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.zakharenkov.shop.service.converter.UserDetailsConverter;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDetailsConverter userDetailsConverter;

    @Override
    public Optional<User> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByEmail(name)
                .map(userDetailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
