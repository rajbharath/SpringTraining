package com.vanaras.service;

import com.vanaras.dao.UserDao;
import com.vanaras.model.User;

import java.sql.SQLException;

public class AuthenticationService {


    private final UserDao userDao;

    public AuthenticationService(UserDao userDao) throws SQLException, ClassNotFoundException {
        this.userDao = userDao;
    }

    public User authenticate(String username, String password) throws Exception {
        if (username == null || username.length() == 0) {
            throw new Exception("Username should be entered");
        }
        if (password == null || password.length() == 0) {
            throw new Exception("Password should be entered");
        }
        User user = userDao.findUserByUsernameAndPassword(username, password);

        if (user == null) throw new Exception("User not found");

        return user;
    }

}
