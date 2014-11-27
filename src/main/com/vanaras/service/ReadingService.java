package com.vanaras.service;


import com.vanaras.dao.ReadingDao;
import com.vanaras.model.Book;
import com.vanaras.model.Permission;
import com.vanaras.model.Reading;
import com.vanaras.model.User;

import java.util.Date;


public class ReadingService {

    private final ReadingDao readingDao;


    public ReadingService(ReadingDao readingDao) {
        this.readingDao = readingDao;
    }

    public void borrowBook(User user, Book book) throws Exception {
        if (!book.isAvailable()) throw new Exception("Book Not available");
        if (!user.isAuthorized(Permission.BORROW_BOOK)) throw new Exception("User not authorized to borrow book");

        Reading reading = new Reading(user, book, new Date(System.currentTimeMillis()));
        book.increaseIssuedCountByOne();
        readingDao.save(reading);
    }

    public void returnBook(User user, Book book) throws Exception {
        if (!user.isAuthorized(Permission.RETURN_BOOK)) throw new Exception("User not authorized to return book");
        Reading reading = readingDao.findBy(user, book);
        if (reading == null) throw new Exception("User currently has no reading on the given book");
        book.decreaseIssuedCountByOne();
        reading.close();
        readingDao.save(reading);
    }
}
