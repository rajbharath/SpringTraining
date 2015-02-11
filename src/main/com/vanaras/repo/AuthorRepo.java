package com.vanaras.repo;

import com.vanaras.model.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Author author) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(author);
    }

    public void update(Author author) {

    }

    public void delete(Author author) {

    }

    public List<Author> findAuthorsByName(String authorName) {
        Session session = sessionFactory.getCurrentSession();
        List<Author> authors = session.createQuery("from author where name like '%" + authorName + "%'").list();
        return authors;
    }
}
