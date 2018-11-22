package com.zakharenkov.shop.database.repository;


import com.zakharenkov.shop.database.config.TestConfiguration;
import com.zakharenkov.shop.database.model.Role;
import com.zakharenkov.shop.database.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    public UserRepository userRepository;

    @Test
    public void check() {
        User save = userRepository.save(User.builder()
                .firstName("sasd")
                .role(Role.USER)
                .lastName("sadwqqq")
                .email("fsqq")
                .number("sqqqq")
                .password("112eq")
                .build());
        Optional<User> user = userRepository.findById(save.getId());
        System.out.println(user.get());
    }
}
