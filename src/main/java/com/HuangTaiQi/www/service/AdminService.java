package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.AdminEntity;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public interface AdminService {
    /**
     * 新增管理员账号
     * @return 返回一个管理员对象
     * @throws Exception 异常
     */
    public AdminEntity createAdminAccount() throws Exception;
    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @return 成功返回管理员对象，失败返回null
     * @throws Exception 异常
     */
    public AdminEntity login(String username, String password) throws Exception;
    /**
     * 改密码
     * @param id 管理员id
     * @param origin 原来的密码
     * @param next  改后的密码
     * @return 是否成功
     * @throws NoSuchAlgorithmException 异常
     * @throws InterruptedException 异常
     * @throws SQLException 异常
     */
    public boolean changePassword(Integer id, String origin, String next) throws NoSuchAlgorithmException, InterruptedException, SQLException;

}
