package ua.mainacademy.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ua.mainacademy.dao.UserDAO;
import ua.mainacademy.model.User;
import ua.mainacademy.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Profile("prod")
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public User create(User user) {
        if (isNull(user.getId()) && userDAO.findAllByLogin(user.getLogin()).isEmpty() && userDAO.findAllByEmail(user.getEmail()).isEmpty()) {
            return userDAO.save(user);
        }
        throw new RuntimeException("User can not be created.");
    }

    @Override
    public User update(User user) {
        if (nonNull(user.getId())) {
            User savedUser = userDAO.findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("User was not found"));
            if (!user.getEmail().equals(savedUser.getEmail())) {
                List<User> result = userDAO.findAllByEmail(user.getEmail());
                if (!result.isEmpty()) {
                    throw new RuntimeException("User email can not be updated");
                }
            }
            if (!user.getLogin().equals(savedUser.getLogin())) {
                List<User> result = userDAO.findAllByLogin(user.getLogin());
                if (!result.isEmpty()) {
                    throw new RuntimeException("User login can not be updated");
                }
            }
            return userDAO.save(user);
        }
        throw new RuntimeException("User can not be updated");
    }

    @Override
    public User findOneById(Integer id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("User was not found"));
    }

    @Override
    public List<User> findAllByLoginAndPassword(String login, String password) {
        return userDAO.findAllByLoginAndPassword(login, password);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public List<User> findAllByFilter(String login, String email, String firstName, String lastName, String phone) {
        // TODO: implement
        return new ArrayList<>();
    }
}
