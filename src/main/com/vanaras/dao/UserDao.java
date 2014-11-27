package com.vanaras.dao;

import com.vanaras.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    void save(User user);

    void update(User user);

    void delete(User user);

    User findUserByUsernameAndPassword(String username,String password);
}
