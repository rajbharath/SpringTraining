package com.vanaras.daoimpl;

import com.vanaras.dao.AuthorDao;
import com.vanaras.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class AuthorDaoImpl implements AuthorDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Author author) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(author);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void delete(Author author) {

    }

    @Override
    public List<Author> findAuthorsByName(String authorName) {
        Session session = sessionFactory.openSession();
        List<Author> authors = session.createQuery("from author where name like '%" + authorName + "%'").list();
        return authors;
    }
}
