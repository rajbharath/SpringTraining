package dao;

import model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao {
    void save(User user);

    void update(User user);

    void delete(User user);

    List<User> findByUsername(String username);
}
