package model;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class UserTest {

    User user = User.builder()
            .id(100L)
            .firstName("Иван")
            .lastName("Иванов")
            .email("ivan@mail.ru")
            .password("111")
            .role(Role.USER)
            .number("375291234567")
            .build();

    User user1 = new User();

    @Test
    public void check() throws Exception {
        assertNotNull(user1);
    }

    @Test
    public void getId() throws Exception {
        assert user.getId() == 100L;
    }

    @Test
    public void getRole() throws Exception {
        assert Role.USER.getName().equals(user.getRole().getName());
    }

    @Test
    public void getFirstName() throws Exception {
        assert "Иван".equals(user.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        assert "Иванов".equals(user.getLastName());
    }

    @Test
    public void getEmail() throws Exception {
        assert "ivan@mail.ru".equals(user.getEmail());
    }

    @Test
    public void getPassword() throws Exception {
        assert "111".equals(user.getPassword());
    }

    @Test
    public void getNumber() throws Exception {
        assert "375291234567".equals(user.getNumber());
    }


}
