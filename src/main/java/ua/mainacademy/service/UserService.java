package ua.mainacademy.service;

import ua.mainacademy.model.User;

import java.util.List;

public interface UserService {

    User create(User user);
    User update(User user);
    User findOneById(Integer id);
    List<User> findAllByLoginAndPassword(String login, String password);
    List<User> findAll();
    void deleteById(Integer id);
    List<User> findAllByFilter(String login, String email, String firstName, String lastName, String phone);
}
