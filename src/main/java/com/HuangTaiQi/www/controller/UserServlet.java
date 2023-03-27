package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.service.UserService;

import javax.servlet.annotation.WebServlet;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;


public class UserServlet {
    public boolean register(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber){
        //假装接受了前端的数据
        boolean isSuccess;
        try {
            isSuccess = new UserService().addUser(username, password, name, studentNumber, electromobileModel, electromobileNumber);
        } catch (SQLException | InterruptedException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return isSuccess;
    }
    public UserEntity login(String username, String password){
        try {
            return new UserService().login(username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<UserEntity> showAuditUser()  {
        try {
            return new UserService().showAuditUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void pass(int id) {
        try {
            new UserService().pass(id,1);
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<UserEntity> showAuditedUser() {
        try {
            return new UserService().showAuditedUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    public void alter(int id, int deleteOrForbid) {
        if(deleteOrForbid==1){
            try {
                new UserService().deleteById(id);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                new UserService().pass(id,0);
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
