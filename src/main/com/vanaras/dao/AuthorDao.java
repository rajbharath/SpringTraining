package com.vanaras.dao;

import com.vanaras.model.Author;

import java.util.List;

public interface AuthorDao {
    public void save(Author author);

    public void update(Author author);

    public void delete(Author author);

    public List<Author> findAuthorsByName(String authorName);
}
