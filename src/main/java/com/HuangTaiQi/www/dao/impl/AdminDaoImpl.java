package com.HuangTaiQi.www.dao.impl;

import com.HuangTaiQi.www.controller.UserServlet;
import com.HuangTaiQi.www.dao.AdminDao;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.utils.DBUtil;
import com.HuangTaiQi.www.utils.sql.SQLBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class AdminDaoImpl implements AdminDao {
    private final Connection connection = DBUtil.getConnection();;
    BaseDao baseDao=new BaseDao(connection);

    private static AdminDaoImpl instance;
    private AdminDaoImpl (){}
    public static synchronized AdminDaoImpl getInstance() {
        if (instance == null) {
            instance = new AdminDaoImpl();
        }
        return instance;
    }
    public AdminEntity selectByUsername(String username) throws Exception {
        String sql=new SQLBuilder("admin").select("*").where("username").buildSelect();
        List list = baseDao.selectByParams(sql, AdminEntity.class, username);
        return list==null?null:(AdminEntity) list.get(0);

    }

    public synchronized void add(AdminEntity admin) throws SQLException, InterruptedException {
        String sql=new SQLBuilder("admin").insert("username").insert("password").buildInsert();
        baseDao.updateCommon(sql,admin.getUsername(),admin.getPassword());
    }

    public AdminEntity getAdminByUsernameAndPassword(String username, String password) throws Exception {
        String sql=new SQLBuilder("admin").select("*").where("username").where("password").buildSelect();
        List list = baseDao.selectByParams(sql, AdminEntity.class, username, password);
        return list==null?null:(AdminEntity) list.get(0);
    }

    public void changePassword(String username, String next) throws SQLException {
        String sql="update admin set password=? where username=?";
        baseDao.updateCommon(sql,next,username);
    }
}
