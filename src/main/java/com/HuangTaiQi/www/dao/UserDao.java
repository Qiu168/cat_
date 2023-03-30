package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.SQLBuilder;

import java.sql.*;
import java.util.List;

public class UserDao extends BaseDao{
    private Connection connection;

    public UserDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }


    public synchronized void addUser(UserEntity user) throws SQLException, InterruptedException {
        String sql = "INSERT INTO users (student_number, name, username, password, electromobile_model, electromobile_number,state,authority_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        updateCommon(sql, user.getStudentNumber(), user.getName(), user.getUsername(),
                user.getPassword(), user.getElectromobileModel(), user.getElectromobileNumber(), user.getState(), user.getAuthorityId());
    }

    public void deleteUserById(int id) throws SQLException, InterruptedException {
//        String sql="select * from users where id=? by update";
//        baseDao.selectByParams(sql,UserEntity.class,id);
        String sql = "DELETE FROM users WHERE id = ?";
        updateCommon(sql,id);
    }

    public UserEntity getUserById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id = ?";
        return (UserEntity) selectByParams(sql, UserEntity.class,id).get(0);
    }
    public UserEntity getUserByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ?";
        List list = selectByParams(sql, UserEntity.class, username);
        return list==null?null:(UserEntity)list.get(0);
    }

    public List<UserEntity> getAllUsers() throws Exception {
        String sql = "SELECT * FROM users";
        return selectByParams(sql, UserEntity.class);
    }

    public void updateUser(UserEntity user) throws SQLException, InterruptedException {
        String sql = "UPDATE users SET student_number = ?, name = ?,  username = ?, password = ?, electromobile_model = ?, electromobile_number = ?,state=?,authority_id=? WHERE id = ?";
        updateCommon(sql,
                user.getStudentNumber(),
                user.getName(),
                user.getUsername(),
                user.getPassword(),
                user.getElectromobileModel(),
                user.getElectromobileNumber(),
                user.getState(),
                user.getAuthorityId(),
                user.getId());
    }

    public UserEntity getUserByUsernameAndPassword(String username, String password) throws Exception {
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        List list = selectByParams(sql, UserEntity.class, username, password);
        return list==null?null:(UserEntity) list.get(0);
    }

    public List<UserEntity> getUsersByState(int state) throws Exception {
        String sql=new SQLBuilder("users").select("*").where("state").buildSelect();
        return selectByParams(sql, UserEntity.class,state);
    }

    public void setUserState(int userId, int state) throws SQLException, InterruptedException {
        String sql="update users set state=? where id=?";
        updateCommon(sql,state,userId);
    }

    public void setUserAuthorityId(int id, int authorityId) throws SQLException, InterruptedException {
        String sql="update users set authority_id=? where id=?";
        updateCommon(sql,authorityId,id);
    }
}
