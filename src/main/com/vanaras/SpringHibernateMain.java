package com.vanaras;

import com.vanaras.model.*;
import com.vanaras.repo.*;
import com.vanaras.service.AdministrativeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@ComponentScan(basePackages = "com.vanaras")
@ImportResource(value = "classpath:/spring.xml")
@EnableAutoConfiguration
@Configuration
@EnableTransactionManagement
public class SpringHibernateMain {

    public static void main(String[] a) {
        ApplicationContext context = SpringApplication.run(SpringHibernateMain.class, a);
        BookRepo bookRepo = context.getBean("bookRepo", BookRepo.class);
        PublisherRepo publisherRepo = context.getBean("publisherRepo", PublisherRepo.class);
        AuthorRepo authorRepo = context.getBean("authorRepo", AuthorRepo.class);
        Book book = new Book("ageless body updatedv3");
        Author author = new Author("deepak updatedv3");
        Publisher publisher = publisherRepo.findByName("Tata Mc graw hill updatedv2");
        book.getAuthors().add(author);
        book.setPublisher(publisher);

        bookRepo.save(book);

        UserRepo userRepo = context.getBean("userRepo", UserRepo.class);
        List<Permission> permissions = new ArrayList<Permission>();
        permissions.add(Permission.ADD_BOOK);
        permissions.add(Permission.REMOVE_BOOK);

        Role role = new Role();
        role.setPermissions(permissions);

//        User user = new User("sakthidheepan", "123456", role);
//        userRepo.save(user);

        User user1 = userRepo.findUserByUsernameAndPassword("sakthidheepan", "123456");
        Book book_1 = bookRepo.findBooksByName("age").get(0);
        ReadingRepo readingRepo = context.getBean("readingRepo", ReadingRepo.class);

        Reading reading = new Reading(user1, book_1, new Date(System.currentTimeMillis()));
        readingRepo.save(reading);
        System.out.println(user1.toString());
        Reading reading_1 = readingRepo.findBy(user1).get(0);
        System.out.println(reading_1.toString());
        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        AdministrativeService administrativeService = context.getBean("administrativeService", AdministrativeService.class);
    }
}
