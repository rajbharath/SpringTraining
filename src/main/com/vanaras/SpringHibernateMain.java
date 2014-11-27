package com.vanaras;

import com.vanaras.dao.*;
import com.vanaras.model.*;
import com.vanaras.service.AdministrativeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpringHibernateMain {

    public static void main(String[] a) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        BookDao bookDao = context.getBean("bookDAO", BookDao.class);
        PublisherDao publisherDao = context.getBean("publisherDAO", PublisherDao.class);
        AuthorDao authorDao = context.getBean("authorDAO", AuthorDao.class);
        Book book = new Book("ageless body updatedv3");
        Author author = new Author("deepak updatedv3");
        Publisher publisher = publisherDao.findByName("Tata Mc graw hill updatedv2");
        book.getAuthors().add(author);
        book.setPublisher(publisher);

        bookDao.save(book);

        UserDao userDao = context.getBean("userDAO", UserDao.class);
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.ADD_BOOK);
        permissions.add(Permission.REMOVE_BOOK);

        User user = new User("sakthidheepan", "123456", permissions);
        userDao.save(user);

        User user1 = userDao.findUserByUsernameAndPassword("sakthidheepan", "123456");
        Book book_1 = bookDao.findBooksByName("age").get(0);
        ReadingDao readingDao = context.getBean("readingDAO", ReadingDao.class);

        Reading reading = new Reading(user1, book_1, new Date(System.currentTimeMillis()));
        readingDao.save(reading);
        System.out.println(user1.toString());
        Reading reading_1 = readingDao.findBy(user1).get(0);
        System.out.println(reading_1.toString());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        AdministrativeService administrativeService = context.getBean("administrativeService", AdministrativeService.class);
        context.close();
    }
}
