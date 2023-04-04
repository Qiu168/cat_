package com.HuangTaiQi.www.utils;

/**
 * 管理链接
 * 开启事务
 */
import com.HuangTaiQi.www.utils.pool.ConnectionPoolManager;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 管理connection和事务
 * @author 14629
 */
public class DBUtil {

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();
    private static final TransactionManager transactionManager= new TransactionManager(getConnection());

    public static Connection getConnection()  {
        Connection conn = tl.get();
        if (conn == null) {
            try {
                conn = ConnectionPoolManager.getConnection();
            } catch (InterruptedException | SQLException e) {
                throw new RuntimeException(e);
            }
            tl.set(conn);
        }
        return conn;
    }


    public static void beginTransaction() throws SQLException {
        transactionManager.beginTransaction();
    }

    public static void commitTransaction() throws SQLException {
        transactionManager.commit();
    }

    public static void rollbackTransaction() throws SQLException {
        transactionManager.rollback();
    }

    public static void close() throws SQLException {
        Connection conn = tl.get();
        if (conn != null) {
            ConnectionPoolManager.closeConnection(conn);
            tl.remove();
        }
    }
}
