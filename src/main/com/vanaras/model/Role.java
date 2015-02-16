package com.vanaras.model;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_description",unique = true)
    @Reference
    @Enumerated(EnumType.STRING)
    private RoleName description;


    @ElementCollection(targetClass = Permission.class)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<Permission> permissions;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoleName getDescription() {
        return description;
    }

    public void setDescription(RoleName description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public boolean contains(Permission permission) {
        return permissions.contains(permission);
    }
}
