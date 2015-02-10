package com.vanaras.service;


import com.vanaras.model.Book;
import com.vanaras.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookSearchService {
    @Autowired
    private BookRepo bookRepo;

    public BookSearchService() {

    }

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> searchBookByName(String name) throws SQLException {
        return bookRepo.findBooksByName(name);
    }
}
