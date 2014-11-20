package com.vanaras.dao;

import com.vanaras.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    void save(User user);

    void update(User user);

    void delete(User user);

    List<User> findByUsername(String username);
}
