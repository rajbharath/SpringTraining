package com.vanaras.repo;

import com.vanaras.model.Book;
import com.vanaras.model.Reading;
import com.vanaras.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReadingRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(Reading reading) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(reading);
    }

    public void update(Reading reading) {

    }

    public void delete(Reading reading) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(reading);
    }

    public List<Reading> findBy(User user) {
        Session session = sessionFactory.getCurrentSession();
        List<Reading> readings = session.createQuery("from Reading R where R.user= :user").setParameter("user", user).list();
        return readings;
    }

    public List<Reading> findBy(Book book) {
        Session session = sessionFactory.getCurrentSession();
        List<Reading> readings = session.createQuery("from Reading R where R.book = :book ").setParameter("book", book).list();
        return readings;
    }

    public Reading findBy(User user, Book book) {
        Session session = sessionFactory.getCurrentSession();
        Reading reading = (Reading) session.createQuery("from Reading R where R.book = :book and user=:user").setParameter("book", book).setParameter("user", user).uniqueResult();
        return reading;
    }
}
