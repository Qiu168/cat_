package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.service.UserService;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;


public class UserServlet extends BaseServlet{
    public boolean register(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber){
        //假装接受了前端的数据
        boolean isSuccess = false;
        try {
            isSuccess = new UserService().addUser(username, password, name, studentNumber, electromobileModel, electromobileNumber);
        } catch (SQLException | InterruptedException | NoSuchAlgorithmException e) {
            handleException(UserServlet.class,e);
        }
        return isSuccess;
    }
    public UserEntity login(String username, String password){
        try {
            return new UserService().login(username,password);
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }
    public List<UserEntity> showAuditUser()  {
        try {
            return new UserService().showAuditUser();
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }

    public void pass(int id) {
        try {
            new UserService().pass(id,1);
        } catch (SQLException | InterruptedException e) {
            handleException(UserServlet.class,e);
        }
    }

    public List<UserEntity> showAuditedUser() {
        try {
            return new UserService().showAuditedUser();
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }

    public void alterState(int id, int deleteOrForbid) {
        if(deleteOrForbid==1){
            try {
                new UserService().deleteById(id);
            } catch (SQLException | InterruptedException e) {
                handleException(UserServlet.class,e);
            }
        }else {
            try {
                new UserService().pass(id,0);
            } catch (SQLException | InterruptedException e) {
                handleException(UserServlet.class,e);
            }
        }
    }

    public void alterAuthority(int id, int authorityId) {
        try {
            new UserService().setAuthority(id,authorityId);
        } catch (SQLException | InterruptedException e) {
            handleException(UserServlet.class,e);
        }
    }
}
