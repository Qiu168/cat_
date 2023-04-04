package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.utils.DBUtil;
import com.HuangTaiQi.www.utils.SQLBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class AdminDao {
    private final Connection connection = DBUtil.getConnection();;
    BaseDao baseDao=new BaseDao(connection);
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

    public void changePassword(Integer id, String next) throws SQLException, InterruptedException {
        String sql="uodate admin set password=? where id=?";
        baseDao.updateCommon(sql,next,id);
    }
}
