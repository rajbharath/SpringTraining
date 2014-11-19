import dao.UserDao;
import model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SpringHibernateMain {
    public static void main(String[] a) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDao userDao = (UserDao) context.getBean("userDAO");
        List<User> users = userDao.findByUsername("priya");
        for (User u : users) {
            System.out.println(u.toString());
        }
        context.close();
    }
}
