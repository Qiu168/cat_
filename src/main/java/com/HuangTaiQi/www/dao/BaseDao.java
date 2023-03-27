package com.HuangTaiQi.www.dao;



import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author huangtaiqi
 */
public class BaseDao {
    private final Connection connection;

    public BaseDao(Connection connection) {
        this.connection = connection;
    }

    private final Logger logger = Logger.getLogger("com.HuangTaiQi.www.utils.BaseDao");
    /**
     *
     * @param sql sql
     * @param cls 返回值的class
     * @param params sql的参数 ,可以为空
     * @return sql查询的所有结果
     * @throws Exception 让调用者知道有异常
     */
    public List selectByParams(String sql, Class cls, Object... params) throws Exception {
        List list=new ArrayList<>();
//        Connection connection = ConnectionPoolManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //填sql
        for(int i=1;i<=params.length;i++){
            preparedStatement.setObject(i,params[i-1]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData metaData = resultSet.getMetaData();
        //数据库数据的列数
        int columnCount = metaData.getColumnCount();
        while(resultSet.next()){
            //select count(*)
            //这里obj不能直接转化为int，运行会报错
            if(cls==Integer.class||cls==int.class){
                Object object = resultSet.getObject(1);
                Long l= (Long) object;
                int ii = l.intValue();
                list.add(ii);
                return list;
            }
            Object t=cls.newInstance();
            for(int i=1;i<=columnCount;i++){
                //现在要往t里填参数
                //列名Label可以获取别名
                String columnLabel = metaData.getColumnLabel(i);
                Object resultSetObject = resultSet.getObject(i);
                String name = getFieldName(columnLabel);
                String relMethodName = "set"+name;
                String columnClassName = metaData.getColumnClassName(i);
                Class<?> columnType = Class.forName(columnClassName);
                Method method2 = cls.getMethod(relMethodName, columnType);
                method2.invoke(t, resultSetObject);
            }
            list.add(t);
        }
        //ConnectionPoolManager.closeConnection(connection);
        return list.size()==0?null:list;
    }
    private String getFieldName(String name){
        StringBuilder builder=new StringBuilder();
        char[] chars = name.toCharArray();
        chars[0]-=32;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='_'){
                chars[i+1]-=32;
                i++;
            }
            builder.append(chars[i]);
        }
        return builder.toString();
    }

    /**
     *
     * @param sql 修改的sql
     * @param args 参数
     * @return 1成功 0失败
     * @throws SQLException 异常
     */
    public boolean updateCommon(String sql, Object ...args) throws SQLException, InterruptedException {
        //Connection conn = ConnectionPoolManager.getConnection();
        PreparedStatement ps = null;
        boolean execute = false;
        try {
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            execute = ps.execute();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "msg: e=" + e.getMessage());
        }
        return execute;
    }
}
