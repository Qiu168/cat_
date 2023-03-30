package com.HuangTaiQi.www.controller;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 14629
 * 获取域名最后的方法名，通过反射调用方法
 */
public class BaseServlet  {
    protected void handleException(Class cls,Exception e) {
        Logger logger=Logger.getLogger(cls.getName());
        logger.log(Level.WARNING,e.toString());
        // 其他处理逻辑
    }
}
