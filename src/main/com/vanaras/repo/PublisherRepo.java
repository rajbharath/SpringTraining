package com.vanaras.repo;

import com.vanaras.model.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class PublisherRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Publisher publisher) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(publisher);
    }

    public void update(Publisher publisher) {

    }

    public void delete(Publisher publisher) {
        Session session = sessionFactory.openSession();
        session.delete(publisher);
    }

    public Publisher findByName(String name) {
        Session session = sessionFactory.openSession();
        Publisher publisher = (Publisher) session.createQuery("from Publisher where lower(name)='"+name.toLowerCase()+"'").uniqueResult();
        return publisher;
    }
}
