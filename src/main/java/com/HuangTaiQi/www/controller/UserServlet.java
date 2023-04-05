package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.service.impl.UserServiceImpl;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;


/**
 * @author 14629
 */
public class UserServlet extends BaseServlet{
    private static UserServlet instance;
    private UserServlet (){}
    public static synchronized UserServlet getInstance() {
        if (instance == null) {
            instance = new UserServlet();
        }
        return instance;
    }
    public boolean register(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber){
        //假装接受了前端的数据
        boolean isSuccess = false;
        try {
            isSuccess = new UserServiceImpl().addUser(username, password, name, studentNumber, electromobileModel, electromobileNumber);
        } catch (SQLException | InterruptedException | NoSuchAlgorithmException e) {
            handleException(UserServlet.class,e);
        }
        return isSuccess;
    }
    public UserEntity login(String username, String password){
        try {
            return new UserServiceImpl().login(username,password);
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }
    public List<UserEntity> showAuditUser()  {
        try {
            return new UserServiceImpl().showAuditUser();
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }

    public void pass(int id) {
        try {
            new UserServiceImpl().setUserState(id,UserEntity.FREE);
        } catch (SQLException  e) {
            handleException(UserServlet.class,e);
        }
    }

    public List<UserEntity> showAuditedUser() {
        try {
            return new UserServiceImpl().showAuditedUser();
        } catch (Exception e) {
            handleException(UserServlet.class,e);
        }
        return null;
    }

    public void alterState(int id, int deleteOrForbid) {
        if(deleteOrForbid==1){
            try {
                new UserServiceImpl().deleteById(id);
            } catch (SQLException | InterruptedException e) {
                handleException(UserServlet.class,e);
            }
        }else {
            try {
                new UserServiceImpl().setUserState(id,UserEntity.FORBID);
            } catch (SQLException  e) {
                handleException(UserServlet.class,e);
            }
        }
    }

    public void alterAuthority(int id, int authorityId) {
        try {
            new UserServiceImpl().setAuthority(id,authorityId);
        } catch (SQLException | InterruptedException e) {
            handleException(UserServlet.class,e);
        }
    }

    public void alterMobile(Integer id, String electromobileModel, String electromobileNumber) {
        try {
            new UserServiceImpl().alterMobile(id,electromobileModel,electromobileNumber);
        } catch (SQLException | InterruptedException e) {
            handleException(UserServlet.class,e);
        }
    }
}
