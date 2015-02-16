package endtoend;

import com.vanaras.service.BookService;
import com.vanaras.service.TestTransaction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class BookServiceTest {

    @Autowired
    BookService bookService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void shouldThrowExceptionWithSearchStringSpaces() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Criteria Should be atleast one character");
        bookService.searchBookByName("     ");

    }

    @Test
    public void shouldThrowExceptionSearchStringIsNull() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Null Criteria Found");
        bookService.searchBookByName(null);

    }
}
