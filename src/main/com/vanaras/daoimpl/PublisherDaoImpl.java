package com.vanaras.daoimpl;

import com.vanaras.dao.PublisherDao;
import com.vanaras.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PublisherDaoImpl implements PublisherDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Publisher publisher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.saveOrUpdate(publisher);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Publisher publisher) {

    }

    @Override
    public void delete(Publisher publisher) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.delete(publisher);
        transaction.commit();
        session.close();
    }

    @Override
    public Publisher findByName(String name) {
        Session session = sessionFactory.openSession();
        Publisher publisher = (Publisher) session.createQuery("from Publisher where lower(name)='"+name.toLowerCase()+"'").uniqueResult();
        session.close();
        return publisher;
    }
}
