package com.vanaras.service;

import com.vanaras.model.Role;
import com.vanaras.model.RoleName;
import com.vanaras.model.User;
import com.vanaras.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleService roleService;

    public UserService() {
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

    public void createUser(String name, String password, RoleName roleName) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        Role role = roleService.findByRoleName(roleName);
        user.setRole(role);
        userRepo.save(user);
    }
}
