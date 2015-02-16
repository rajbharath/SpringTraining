package endtoend;

import com.vanaras.model.Permission;
import com.vanaras.model.RoleName;
import com.vanaras.model.User;
import com.vanaras.service.RoleService;
import com.vanaras.service.TestTransaction;
import com.vanaras.service.UserService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@TestTransaction
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldReturnUserForValidCredentials() throws Exception {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(Permission.ADD_BOOK);
        permissions.add(Permission.REMOVE_BOOK);
        roleService.createRole(RoleName.ADMIN, permissions);
        userService.createUser("Raj Bharath", "123456", RoleName.ADMIN);
        User user = userService.authenticate("Raj Bharath", "123456");
        assertTrue("Username does not match", user.getUsername().equals("Raj Bharath"));
        assertTrue("Password does not match", user.getPassword().equals("123456"));
        assertTrue("Role does not match", user.getRole().getDescription().equals(RoleName.ADMIN));
    }

    @Test
    public void shouldThrowExceptionForInvalidCredentials() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("User not found");
        userService.authenticate("invalidusername", "invalidpassword");
    }

    @Test
    public void shouldThrowExceptionUsernameAndPasswordSpaces() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Username should be entered");
        userService.authenticate("", "");
    }

    @Test
    public void shouldThrowExceptionUsernameSpaces() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Username should be entered");
        userService.authenticate("", "123465");
    }

    @Test
    public void shouldThrowExceptionPasswordSpaces() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage("Password should be entered");
        userService.authenticate("rbrajbharath", "");
    }


}
