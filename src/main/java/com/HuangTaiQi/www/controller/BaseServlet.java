package com.HuangTaiQi.www.controller;



import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 14629
 * 获取域名最后的方法名，通过反射调用方法
 */
public class BaseServlet  {
    protected <T> void handleException(Class<T> cls, Exception e){
        Logger logger=Logger.getLogger(cls.getName());
        try {
            // 判断异常类型
            if (e instanceof SQLException) {
                if (e.getMessage().contains("doesn't exist")) {
                    logger.log(Level.WARNING,"file not find An error occurred in "+cls.getName()+" e = {0}", e.getMessage());
                }
            } else if (e instanceof IOException) {
                // 处理 IO 异常
                logger.log(Level.WARNING,"An error occurred in "+cls.getName()+" e = {0}", e.getMessage());
            } else {
                logger.log(Level.WARNING,"An error occurred in "+cls.getName()+" e = {0}", e.getMessage());
            }
        } catch (Exception ex) {
            // 处理异常时出现异常
            logger.log(Level.WARNING,"An error occurred in "+cls.getName()+" e = {0}", e.getMessage());
        }
    }

}
