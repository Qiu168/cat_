import com.HuangTaiQi.www.dao.AdminDao;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.service.AdminService;
import com.HuangTaiQi.www.service.impl.AdminServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AdminServiceTest {
    AdminDao adminDao=new AdminDao();
    AdminService adminService=new AdminServiceImpl();

    @Test
    public void testSelectByUsername() throws Exception {
        AdminEntity admin = adminDao.selectByUsername("123456");
        assertNotNull(admin);
    }
    @Test
    public void test() throws Exception {
        AdminEntity login = adminService.login("123456", "123456");
        assertNotNull(login);
    }

}
