package com.vanaras.dao;

import com.vanaras.model.Book;

import java.util.List;

public interface BookDao {
    public void save(Book book);

    public void update(Book book);

    public void delete(Book book);

    public List<Book> findBooksByName(String bookName);
}
