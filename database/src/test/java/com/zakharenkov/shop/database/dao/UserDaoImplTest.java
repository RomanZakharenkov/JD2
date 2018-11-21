package com.zakharenkov.shop.database.dao;

import com.zakharenkov.shop.database.configuration.DatabaseConfiguration;
import com.zakharenkov.shop.database.model.Role;
import com.zakharenkov.shop.database.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DatabaseConfiguration.class)
@Transactional
public class UserDaoImplTest {

    @Autowired
    private UserDaoImpl userDao;

    @Test
    public void checkSave() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ii@mail.ru")
                .password("1111")
                .build();

        Long saveId = userDao.save(user);
        assertNotNull(saveId);
    }

    @Test
    public void checkFindById() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("iivan@mail.ru")
                .password("1111")
                .build();

        Long saveId = userDao.save(user);
        User userFind = userDao.findById(saveId).get();

        assertNotNull(userFind);
    }

    @Test
    public void checkUpdate() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("vallkkkln@mail.ru")
                .password("1111")
                .build();

        Long saveId = userDao.save(user);
        User findUser = userDao.findById(saveId).get();

        findUser.setFirstName("Petr");
        userDao.update(findUser);

        User secondFindUser = userDao.findById(saveId).get();

        assertEquals("Petr", secondFindUser.getFirstName());
    }

    @Test
    public void checkDelete() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("van@mail.ru")
                .password("1111")
                .build();
        Long saveId = userDao.save(user);
        User userFind = userDao.findById(saveId).get();
        userDao.delete(userFind);

        Optional<User> findDeleteUser = userDao.findById(saveId);
        assertFalse(findDeleteUser.isPresent());
    }

    @Test
    public void checkFindAll() {
        User firstUser = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("vawdqqqqqan@mail.ru")
                .password("1111")
                .build();
        User secondUser = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("vasdawwdan@mail.ru")
                .password("1111")
                .build();
        Long saveFirstUserId = userDao.save(firstUser);
        Long saveSecondUserId = userDao.save(secondUser);

        List<User> users = userDao.findAll();

        assertEquals(2, users.size());
    }

}