package com.vanaras.daoimpl;

import com.vanaras.dao.ReadingDao;
import com.vanaras.model.Book;
import com.vanaras.model.Reading;
import com.vanaras.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ReadingDaoImpl implements ReadingDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Reading reading) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(reading);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Reading reading) {

    }

    @Override
    public void delete(Reading reading) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.delete(reading);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Reading> findBy(User user) {
        Session session = sessionFactory.openSession();
        List<Reading> readings = session.createQuery("from Reading R where R.user= :user").setParameter("user", user).list();
        session.close();
        return readings;
    }

    @Override
    public List<Reading> findBy(Book book) {
        Session session = sessionFactory.openSession();
        List<Reading> readings = session.createQuery("from Reading R where R.book = :book ").setParameter("book", book).list();
        session.close();
        return readings;
    }

    @Override
    public Reading findBy(User user, Book book) {
        Session session = sessionFactory.openSession();
        Reading reading = (Reading) session.createQuery("from Reading R where R.book = :book and user=:user").setParameter("book", book).setParameter("user", user).uniqueResult();
        session.close();
        return reading;
    }
}
