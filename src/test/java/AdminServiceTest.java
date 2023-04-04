import com.HuangTaiQi.www.dao.impl.AdminDaoImpl;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.service.AdminService;
import com.HuangTaiQi.www.service.impl.AdminServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AdminServiceTest {
    AdminDaoImpl adminDaoImpl =new AdminDaoImpl();
    AdminService adminService=new AdminServiceImpl();

    @Test
    public void testSelectByUsername() throws Exception {
        AdminEntity admin = adminDaoImpl.selectByUsername("123456");
        assertNotNull(admin);
    }
    @Test
    public void testLogin() throws Exception {
        AdminEntity login = adminService.login("123456", "123456");
        assertNotNull(login);
    }
    @Test
    public void testCreateAdminAccount() throws Exception {
        AdminEntity adminAccount = adminService.createAdminAccount();
        assertNotNull(adminAccount);
    }


}
