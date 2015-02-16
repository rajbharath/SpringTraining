package com.vanaras.repo;

import com.vanaras.model.Role;
import com.vanaras.model.RoleName;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepo {

    @Autowired
    SessionFactory sessionFactory;

    public void create(Role role) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(role);
    }

    public Role findByRoleName(RoleName roleName) {
        Session session = sessionFactory.getCurrentSession();
        return (Role) session.createQuery("from Role R where R.description = :roleName").setParameter("roleName", roleName).uniqueResult();
    }
}
