package com.vanaras.repo;

import com.vanaras.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    public void update(Book book) {

    }

    public void delete(Book book) {

    }

    public List<Book> findBooksByName(String bookName) {
        Session session = sessionFactory.getCurrentSession();
        List<Book> books = session.createQuery("from Book where lower(name) like '%" + bookName.toLowerCase() + "%'").list();
        return books;
    }
}
