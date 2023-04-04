package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.service.AdminService;
import com.HuangTaiQi.www.service.impl.AdminServiceImpl;

/**
 * @author 14629
 */
public class AdminServlet extends BaseServlet{
    private final String KEY="123";
    private final AdminService adminService=new AdminServiceImpl();
    public AdminEntity register(String key) {
        //想怎么操作？，可以从数据库拿？可以加密？这里就简单写写了。
        if(KEY.equals(key)){
            //生成账号密码。
            try {
                return adminService.createAdminAccount();
            } catch (Exception e) {
                handleException(AdminServlet.class,e);
            }
        }
        return null;
    }
    public AdminEntity login(String username,String password){
        try {
            return adminService.login(username,password);
        } catch (Exception e) {
            handleException(AdminServlet.class,e);
        }
        return null;
    }

    public boolean ChangePassword(String username, String origin, String next) {
        try {
            return adminService.changePassword(username,origin,next);
        } catch (Exception e) {
            handleException(AdminServlet.class,e);
        }
        return false;
    }

    public void showReport(int year, int month, int day) {
        try {
            adminService.showReport(year,month,day);
        } catch (Exception e) {
            handleException(AdminServlet.class,e);
        }
    }
}
