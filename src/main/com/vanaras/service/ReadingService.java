package com.vanaras.service;


import com.vanaras.model.*;
import com.vanaras.repo.ReadingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class ReadingService {

    @Autowired
    private ReadingRepo readingRepo;


    public ReadingService() {
    }

    public ReadingRepo getReadingRepo() {
        return readingRepo;
    }

    public void setReadingRepo(ReadingRepo readingRepo) {
        this.readingRepo = readingRepo;
    }

    public void borrowBook(User user, Book book) throws Exception {
        if (!book.isAvailable()) throw new Exception("Book Not available");
        if (!user.isAuthorized(Permission.BORROW_BOOK)) throw new Exception("User not authorized to borrow book");

        Reading reading = new Reading(user, book, new Date(System.currentTimeMillis()));
        book.setStatus(BookStatus.ISSUED);
        readingRepo.save(reading);
    }

    public void returnBook(User user, Book book) throws Exception {
        if (!user.isAuthorized(Permission.RETURN_BOOK)) throw new Exception("User not authorized to return book");
        Reading reading = readingRepo.findBy(user, book);
        if (reading == null) throw new Exception("User currently has no reading on the given book");
        book.setStatus(BookStatus.AVAILABLE);
        reading.close();
        readingRepo.save(reading);
    }
}
