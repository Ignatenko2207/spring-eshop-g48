package ua.mainacademy.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.mainacademy.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    void createAndDeleteUser() {
        User user = User.builder()
                .login("test_login")
                .password("test_pass")
                .firstName("f_name")
                .lastName("l_name")
                .phone("+380505555555")
                .email("my.email@mail.com")
                .build();
        User savedUser = userDAO.save(user);

        assertNotNull(savedUser.getId());

        userDAO.delete(savedUser);
    }

    @Test
    void findByLoginAndPassword() {
        User user = User.builder()
                .login("test_login")
                .password("test_pass")
                .firstName("f_name")
                .lastName("l_name")
                .phone("+380505555555")
                .email("my.email@mail.com")
                .build();
        User savedUser = userDAO.save(user);

        List<User> users = userDAO.findAllByLoginAndPassword("test_login", "test_pass");
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(users.get(0).getId(), savedUser.getId());

        userDAO.delete(savedUser);
    }


    @Test
    void findWithQuery() {
        User user = User.builder()
                .login("test_login")
                .password("test_pass")
                .firstName("f_name")
                .lastName("l_name")
                .phone("+380505555555")
                .email("my.email@mail.com")
                .build();
        User savedUser = userDAO.save(user);

        List<User> users = userDAO.findAllByEmail("my.email@mail.com");
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(users.get(0).getId(), savedUser.getId());

        userDAO.delete(savedUser);
    }

}