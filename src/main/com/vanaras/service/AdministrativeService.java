package com.vanaras.service;


import com.vanaras.model.*;
import com.vanaras.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdministrativeService {

    @Autowired
    private BookRepo bookRepo;

    public AdministrativeService() {
    }

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void addBook(User user, String name, List<String> authorNames, String publisherName, int noOfCopies, int issuedCount) throws Exception {
        if (!user.isAuthorized(Permission.ADD_BOOK)) throw new Exception("User Not Authorized");
        Publisher publisher = new Publisher(publisherName);

        Book book = new Book(name);
        book.setPublisher(publisher);

        List<Author> authors = new ArrayList<>();
        for (String authorName : authorNames) {
            book.getAuthors().add(new Author(authorName));
        }
        bookRepo.save(book);
    }

    public void removeBook(User user, Book book) throws Exception {
        if (!user.isAuthorized(Permission.REMOVE_BOOK)) throw new Exception("User not authorized for this operation");
        bookRepo.delete(book);
    }

}
