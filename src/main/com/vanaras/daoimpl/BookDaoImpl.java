package com.vanaras.daoimpl;

import com.vanaras.dao.BookDao;
import com.vanaras.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void save(Book book) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(book);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public List<Book> findBooksByName(String bookName) {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("from Book where lower(name) like '%" + bookName.toLowerCase() + "%'").list();
        return books;
    }
}
