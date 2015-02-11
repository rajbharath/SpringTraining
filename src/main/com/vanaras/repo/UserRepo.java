package com.vanaras.repo;

import com.vanaras.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
    }

    public void update(User user) {

    }

    public void delete(User user) {

    }

    public User findUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User where username = '" + username + "' and password = '" + password + "'").uniqueResult();
        return user;
    }
}
