package com.vanaras.service;

import com.vanaras.model.Permission;
import com.vanaras.model.Role;
import com.vanaras.model.RoleName;
import com.vanaras.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepo roleRepo;

    public void createRole(RoleName roleName, List<Permission> permissions) {
        Role role = new Role();
        role.setDescription(roleName);
        role.setPermissions(permissions);
        roleRepo.create(role);
    }

    public Role findByRoleName(RoleName roleName) {
        return roleRepo.findByRoleName(roleName);
    }
}
