package com.vanaras.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@SecondaryTables({@SecondaryTable(name = "\"user\""), @SecondaryTable(name = "book")})
@Table(name = "reading", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "book_id"}))
public class Reading {

    public final static long RENTAL_PERIOD = 1000 * 60 * 60 * 24 * 15;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "borrowed_date")
    private Date borrowedDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "returned_date")
    private Date returnedDate;

    Reading() {
    }

    public Reading(User user, Book book, Date borrowedDate) {
        this.user = user;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = new Date(borrowedDate.getTime() + RENTAL_PERIOD);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "user=" + user +
                ", book=" + book +
                ", borrowedDate=" + borrowedDate +
                ", dueDate=" + dueDate +
                ", returnedDate=" + returnedDate +
                '}';
    }

    public void close() {
        if (returnedDate == null)
            returnedDate = new Date(System.currentTimeMillis());
    }
}
