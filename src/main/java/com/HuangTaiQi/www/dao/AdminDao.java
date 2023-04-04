package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.po.ChargingPileEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 管理员
 * @author 14629
 */
public interface AdminDao {
    /**
     * 通过username查询管理员
     * @param username 账号
     * @return 管理员对象
     * @throws Exception 异常
     */
    public AdminEntity selectByUsername(String username) throws Exception;

    /**
     * 新增管理员
     * @param admin 管理员对象
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void add(AdminEntity admin) throws SQLException, InterruptedException;

    /**
     * 通过账号密码查询管理员，登录
     * @param username 账号
     * @param password 密码
     * @return 管理员对象
     * @throws Exception 异常
     */
    public AdminEntity getAdminByUsernameAndPassword(String username, String password) throws Exception;

    /**
     * 改密码
     * @param username 账号
     * @param next 改后的密码
     * @throws SQLException 异常
     */
    public void changePassword(String username, String next) throws SQLException;


}
