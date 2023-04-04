package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.service.impl.AdminServiceImpl;

public class AdminServlet extends BaseServlet{
    private final String KEY="123";
    public AdminEntity register(String key) {
        //想怎么操作？，可以从数据库拿？可以加密？这里就简单写写了。
        if(KEY.equals(key)){
            //生成账号密码。
            try {
                return new AdminServiceImpl().createAdminAccount();
            } catch (Exception e) {
                handleException(AdminServlet.class,e);
            }
        }
        return null;
    }
    public AdminEntity login(String username,String password){
        try {
            return new AdminServiceImpl().login(username,password);
        } catch (Exception e) {
            handleException(AdminServlet.class,e);
        }
        return null;
    }

    public boolean ChangePassword(Integer id, String origin, String next) {
        try {
            return new AdminServiceImpl().changePassword(id,origin,next);
        } catch (Exception e) {
            handleException(AdminServlet.class,e);
        }
        return false;
    }
}
