import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.service.UserService;
import com.HuangTaiQi.www.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class UserServiceTest {
    UserService userService=new UserServiceImpl();
    @Test
    public void testLogin() throws Exception {
        UserEntity login = userService.login("123456", "123456");
        assertNotNull(login);
    }
    @Test
    public void testShowAuditedUser() throws Exception {
        List<UserEntity> userEntities = userService.showAuditedUser();
        assertNotNull(userEntities);
    }
    @Test
    public void testShowAuditUser() throws Exception {
        List<UserEntity> userEntities = userService.showAuditUser();
        assertNotNull(userEntities);
    }
}
