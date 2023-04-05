package com.HuangTaiQi.www.service.impl;

import com.HuangTaiQi.www.dao.impl.AdminDaoImpl;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.service.AdminService;
import com.HuangTaiQi.www.utils.DBUtil;

import com.HuangTaiQi.www.utils.TextLog;
import com.HuangTaiQi.www.utils.code.Md5Utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 管理员
 * @author 14629
 */
public class AdminServiceImpl implements AdminService {
    private final int usernameLength=8;
    private final AdminDaoImpl adminDaoImpl = AdminDaoImpl.getInstance();
    /**
     * 新增管理员账号
     * @return 返回一个管理员对象
     * @throws Exception 异常
     */
    public synchronized AdminEntity createAdminAccount() throws Exception {
        DBUtil.beginTransaction();
        Map<String, String> map = new HashMap<>();
        // 使用线程安全的 SecureRandom 生成随机数
        SecureRandom random = new SecureRandom();
        StringBuilder username = new StringBuilder(usernameLength);
        do {
            // 清空 StringBuilder
            username.setLength(0);
            for (int i = 0; i < usernameLength; i++) {
                // 随机生成数字
                username.append(random.nextInt(10));
            }
            // 使用 Map.putIfAbsent() 方法检查用户名是否已经存在
        } while (map.putIfAbsent(username.toString(), UUID.randomUUID().toString()) != null || adminDaoImpl.selectByUsername(username.toString()) != null);
        // 将生成的用户名和密码记录到数据库中
        AdminEntity admin = new AdminEntity(username.toString(), map.get(username.toString()));
        try {
            adminDaoImpl.add(new AdminEntity(admin.getUsername(), Md5Utils.encode(admin.getPassword())));
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
        TextLog.add("新增admin，账号为"+username);
        return admin;
    }

    /**
     * 登录方法
     * @param username 用户名
     * @param password 密码
     * @return 成功返回管理员对象，失败返回null
     * @throws Exception 异常
     */
    public AdminEntity login(String username, String password) throws Exception {
        AdminEntity admin = adminDaoImpl.getAdminByUsernameAndPassword(username, Md5Utils.encode(password));
        DBUtil.close();
        return admin;
    }

    /**
     * 改密码
     * @param username 管理员账号
     * @param origin 原来的密码
     * @param next  改后的密码
     * @return 是否成功
     * @throws NoSuchAlgorithmException 异常
     * @throws InterruptedException 异常
     * @throws SQLException 异常
     */
    public boolean changePassword(String username, String origin, String next) throws Exception {
        DBUtil.beginTransaction();
        if(adminDaoImpl.getAdminByUsernameAndPassword(username,Md5Utils.encode(origin))!=null){
            try {
                adminDaoImpl.changePassword(username,next);
            } catch (SQLException e) {
                DBUtil.rollbackTransaction();
                throw new RuntimeException(e);
            }
        }else{
            return false;
        }
        DBUtil.commitTransaction();
        DBUtil.close();
        return true;
    }

    public void showReport(int year, int month, int day) throws Exception {
        TextLog.readTxt(year,month,day);
    }
}
