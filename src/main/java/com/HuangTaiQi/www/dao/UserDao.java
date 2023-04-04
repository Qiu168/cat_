package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.UserEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * user
 * @author 14629
 */
public interface UserDao {
    /**
     * 新增user
     * @param user user对象
     * @throws SQLException 异常
     */
    void addUser(UserEntity user) throws SQLException;

    /**
     * 删除user
     * @param id userid
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void deleteUserById(int id) throws SQLException, InterruptedException;

    /**
     * 获取user
     * @param id userId
     * @return user对象
     * @throws Exception 异常
     */
    UserEntity getUserById(int id) throws Exception;

    /**
     * 获取user
     * @param username 账号
     * @return user对象
     * @throws Exception 异常
     */
    UserEntity getUserByUsername(String username) throws Exception;

    /**
     * 获取所有user
     * @return 返回user集合
     * @throws Exception 异常
     */
    List<UserEntity> getAllUsers() throws Exception;

    /**
     * 修改user
     * @param user 改后的user
     * @throws SQLException 异常
     */
    void updateUser(UserEntity user) throws SQLException;

    /**
     * 通过账号密码获取user，登录
     * @param username 账号
     * @param password 密码
     * @return user对象
     * @throws Exception 异常
     */
    UserEntity getUserByUsernameAndPassword(String username, String password) throws Exception;

    /**
     * 获取审核是否通过的user
     * @param state 状态
     * @return 返回值
     * @throws Exception 异常
     */
    List<UserEntity> getUsersByState(int state) throws Exception;

    /**
     * 设置user的状态
     * @param userId id
     * @param state 设置的状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void setUserState(int userId, int state) throws SQLException, InterruptedException;

    /**
     * 设置user的权限等级
     * @param id userid
     * @param authorityId 权限等级
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void setUserAuthorityId(int id, int authorityId) throws SQLException, InterruptedException;

    /**
     * 修改电动车
     * @param id userid
     * @param electromobileModel 电动车型号
     * @param electromobileNumber 电动车牌号
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void alterMobile(Integer id, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException;
}
