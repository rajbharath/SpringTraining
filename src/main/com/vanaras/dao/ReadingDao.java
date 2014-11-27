package com.vanaras.dao;

import com.vanaras.model.Book;
import com.vanaras.model.Reading;
import com.vanaras.model.User;

import java.util.List;

public interface ReadingDao {
    public void save(Reading reading);

    public void update(Reading reading);

    public void delete(Reading reading);

    public List<Reading> findBy(User user);

    public List<Reading> findBy(Book book);

    public Reading findBy(User user, Book book);
}
