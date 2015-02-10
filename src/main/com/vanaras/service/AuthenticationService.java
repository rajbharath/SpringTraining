package com.vanaras.service;

import com.vanaras.model.User;
import com.vanaras.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {


    @Autowired
    private UserRepo userRepo;

    public AuthenticationService() {
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User authenticate(String username, String password) throws Exception {
        if (username == null || username.length() == 0) {
            throw new Exception("Username should be entered");
        }
        if (password == null || password.length() == 0) {
            throw new Exception("Password should be entered");
        }
        User user = userRepo.findUserByUsernameAndPassword(username, password);

        if (user == null) throw new Exception("User not found");

        return user;
    }

}
