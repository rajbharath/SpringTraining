package com.vanaras;

import com.vanaras.dao.BookDao;
import com.vanaras.dao.UserDao;
import com.vanaras.model.Author;
import com.vanaras.model.Book;
import com.vanaras.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringHibernateMain {
    public static void main(String[] a) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = (UserDao) context.getBean("userDAO");
        BookDao bookDao = context.getBean("bookDAO", BookDao.class);
//        User user = new User();
//        user.setUsername("Priyadharshani");
//        userDao.save(user);
        List<User> users = userDao.findByUsername("priya");
        Book book = new Book("E PAA");
        Author author = new Author("Fowler");
        book.getAuthors().add(author);
        author.getBooks().add(book);
        bookDao.save(book);
        System.out.println("===========================USER================================");
        for (User u : users) {
            System.out.println("Printing" + u.toString());
        }


        List<Book> books = bookDao.findBooksByName("pa");

        System.out.println("===========================BOOK================================");
        for (Book b : books) {
            System.out.println("Printing" + b.toString());
        }
        context.close();
    }
}
