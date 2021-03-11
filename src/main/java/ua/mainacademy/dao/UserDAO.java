package ua.mainacademy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.mainacademy.model.User;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findAllByLoginAndPassword(String login, String password);

    List<User> findAllByEmail(String userEmail);

    List<User> findAllByLogin(String login);
}
