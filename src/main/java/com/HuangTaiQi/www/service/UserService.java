package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.dao.UserDao;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.pool.ConnectionPoolManager;
import com.HuangTaiQi.www.utils.code.Md5Utils;
import com.HuangTaiQi.www.utils.TransactionManager;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 14629
 */
public class UserService {
    private Connection connection;
    public boolean addUser(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException, NoSuchAlgorithmException {
        UserEntity user=new UserEntity();
        user.setElectromobileModel(electromobileModel);
        user.setName(name);
        user.setElectromobileNumber(electromobileNumber);
        user.setPassword(Md5Utils.encode(password));
        user.setUsername(username);
        user.setStudentNumber(studentNumber);
        user.setState(false);
        user.setAuthorityId(1);
        connection= ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            UserDao userDao = new UserDao(connection);
            //还可以加studentNumber，学号唯一之类的。
            UserEntity userByUsername = userDao.getUserByUsername(username);
            if(userByUsername==null){
                userDao.addUser(user);
            }else {
                return false;
            }
        } catch (Exception e) {
            transactionManager.rollback();
            ConnectionPoolManager.closeConnection(connection);
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
        return true;
    }

    public UserEntity login(String username, String password) throws Exception {
        connection=ConnectionPoolManager.getConnection();
        UserEntity userByUsernameAndPassword = new UserDao(connection).getUserByUsernameAndPassword(username, Md5Utils.encode(password));
        ConnectionPoolManager.closeConnection(connection);
        if( userByUsernameAndPassword != null && userByUsernameAndPassword.getState()){
            return userByUsernameAndPassword;
        }else {
            return null;
        }
    }

    public List<UserEntity> showAuditUser() throws Exception {
        connection=ConnectionPoolManager.getConnection();
        List<UserEntity> usersWhereStateZero = new UserDao(connection).getUsersByState(0);
        ConnectionPoolManager.closeConnection(connection);
        return usersWhereStateZero;
    }

    public void pass(int UserId,int state) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new UserDao(connection).setUserState(UserId,state);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public List<UserEntity> showAuditedUser() throws Exception {
        connection=ConnectionPoolManager.getConnection();
        List<UserEntity> usersWhereStateOne = new UserDao(connection).getUsersByState(1);
        ConnectionPoolManager.closeConnection(connection);
        return usersWhereStateOne;
    }

    public void deleteById(int id) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new UserDao(connection).deleteUserById(id);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void setAuthority(int id, int authorityId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new UserDao(connection).setUserAuthorityId(id,authorityId);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }
}
