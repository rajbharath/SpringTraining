package com.vanaras.service;


import com.vanaras.dao.BookDao;
import com.vanaras.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookSearchService {
    private BookDao bookDao;

    public BookSearchService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> searchBookByName(String name) throws SQLException {
        return bookDao.findBooksByName(name);
    }
}
