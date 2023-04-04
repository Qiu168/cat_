package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.UserEntity;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 新增user
     * @param username 账号
     * @param password 密码
     * @param name 名称
     * @param studentNumber 学号
     * @param electromobileModel 电动车型号
     * @param electromobileNumber 电动车牌号
     * @return 返回是否添加成功
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public boolean addUser(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException, NoSuchAlgorithmException;
    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return 成功返回user对象，失败返回null
     * @throws Exception 异常
     */
    public UserEntity login(String username, String password) throws Exception;
    /**
     * 展示未审核通过的user
     * @return 返回集合
     * @throws Exception 异常
     */
    public List<UserEntity> showAuditUser() throws Exception;
    /**
     * 将用户的状态设值。用于审核通过用户
     * @param UserId 通过的用户id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */

    public void pass(int UserId, int state) throws SQLException, InterruptedException;
    /**
     * 展示审核通过的用户
     * @return 返回审核通过的用户
     * @throws Exception 异常
     */
    public List<UserEntity> showAuditedUser() throws Exception;
    /**
     * 删除用户
     * @param id 被删除用户的id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteById(int id) throws SQLException, InterruptedException;
    /**
     * 设置权限
     * @param id 用户的id
     * @param authorityId 权限等级
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setAuthority(int id, int authorityId) throws SQLException, InterruptedException;
    /**
     * 修改电动车的型号和牌号，修改后要重新审核
     * @param id 用户的id
     * @param electromobileModel 型号
     * @param electromobileNumber 牌号
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void alterMobile(Integer id, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException;
}

