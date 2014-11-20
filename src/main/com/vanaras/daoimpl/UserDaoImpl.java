package com.vanaras.daoimpl;

import com.vanaras.dao.UserDao;
import com.vanaras.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> findByUsername(String username) {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User where lower(username) like '%" + username.toLowerCase() + "%'").list();
        session.close();
        return users;
    }
}
