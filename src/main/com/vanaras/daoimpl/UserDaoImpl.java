package com.vanaras.daoimpl;

import com.vanaras.dao.UserDao;
import com.vanaras.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.openSession();
        org.hibernate.Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
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
    public User findUserByUsernameAndPassword(String username,String password) {
        Session session = sessionFactory.openSession();
        User user = (User) session.createQuery("from User where username = '"+username+"' and password = '"+password+"'").uniqueResult();
        session.close();
        return user;
    }
}
