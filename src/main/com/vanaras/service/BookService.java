package com.vanaras.service;


import com.vanaras.model.Book;
import com.vanaras.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    public BookService() {

    }

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Book> searchBookByName(String name) throws Exception {
        if (name == null) throw new Exception("Null Criteria Found");
        if (name.trim().length() < 1) throw new Exception("Criteria Should be atleast one character");
        return bookRepo.findBooksByName(name);
    }

    public Book findById(int id) {
        return bookRepo.findById(id);
    }
}
