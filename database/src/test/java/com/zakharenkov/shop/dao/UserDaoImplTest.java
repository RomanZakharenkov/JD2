package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Role;
import com.zakharenkov.shop.model.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void checkSave() {
        User user = User.builder()
                .role(Role.USER)
                .firstName("Ivan")
                .lastName("Ivanov")
                .email("ii@mail.ru")
                .password("1111")
                .build();

        Long saveId = UserDaoImpl.getInstance().save(user);
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

        Long saveId = UserDaoImpl.getInstance().save(user);
        User userFind = UserDaoImpl.getInstance().findById(saveId);

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

        Long saveId = UserDaoImpl.getInstance().save(user);
        User findUser = UserDaoImpl.getInstance().findById(saveId);

        findUser.setFirstName("Petr");
        UserDaoImpl.getInstance().update(findUser);

        User secondFindUser = UserDaoImpl.getInstance().findById(saveId);

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
        Long saveId = UserDaoImpl.getInstance().save(user);
        User userFind = UserDaoImpl.getInstance().findById(saveId);
        UserDaoImpl.getInstance().delete(userFind);

        User findDeletedUser = UserDaoImpl.getInstance().findById(saveId);
        assertNull(findDeletedUser);
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
        Long saveFirstUserId = UserDaoImpl.getInstance().save(firstUser);
        Long saveSecondUserId = UserDaoImpl.getInstance().save(secondUser);

        List<User> users = UserDaoImpl.getInstance().findAll();

        assertEquals(2, users.size());
    }

}