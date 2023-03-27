package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.dao.AdminDao;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.utils.ConnectionPoolManager;
import com.HuangTaiQi.www.utils.Md5Utils;
import com.HuangTaiQi.www.utils.TransactionManager;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AdminService {
    private final int usernameLength=8;
    private Connection connection;
    public synchronized AdminEntity createAdminAccount() throws Exception {
        connection= ConnectionPoolManager.getConnection();
        AdminDao adminDao = new AdminDao(connection);
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

        } while (map.putIfAbsent(username.toString(), UUID.randomUUID().toString()) != null || adminDao.selectByUsername(username.toString()) != null);
        // 将生成的用户名和密码记录到数据库中
        AdminEntity admin = new AdminEntity(username.toString(), map.get(username.toString()));
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            adminDao.add(new AdminEntity(admin.getUsername(), Md5Utils.encode(admin.getPassword())));
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
        return admin;
    }

    public AdminEntity login(String username, String password) throws Exception {
        connection=ConnectionPoolManager.getConnection();
        AdminEntity admin = new AdminDao(connection).getAdminByUsernameAndPassword(username, Md5Utils.encode(password));
        ConnectionPoolManager.closeConnection(connection);
        return admin;
    }
}
