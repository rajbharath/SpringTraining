package endtoend;

import com.vanaras.model.*;
import com.vanaras.service.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class AdministrativeServiceTest {

    @Autowired
    AdministrativeService administrativeService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    BookService bookService;

    private User user;

    @Before
    public void setUp() throws Exception {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.ADD_BOOK);
        permissions.add(Permission.REMOVE_BOOK);
        roleService.createRole(RoleName.ADMIN, permissions);
        userService.createUser("Raj Bharath", "123456", RoleName.ADMIN);
        user = userService.authenticate("Raj Bharath","123456");
    }


    @Test
    public void endToEndShouldAddBookWithValidBookDetails() throws Exception {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Martin Fowler"));
        authors.add(new Author("fowler"));
        Book expected = new Book();
        expected.setAuthors(authors);
        expected.setPublisher(new Publisher("Addison-Wesly"));
        expected.setName("P EAA");
        expected.setStatus(BookStatus.AVAILABLE);
        administrativeService.addBook(user, "P EAA", Arrays.asList(new String[]{"Martin Fowler", "fowler"}), "Addison-Wesly");
        Book actual = bookService.searchBookByName("P EAA").get(0);
        assertTrue("end To End Should Add Book With Valid Book Details failed", expected.getName().equals(actual.getName()));
    }

    @After
    public void tearDown() throws Exception {
    }
}
