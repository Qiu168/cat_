package com.HuangTaiQi.www.service.impl;

import com.HuangTaiQi.www.dao.impl.UserDaoImpl;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.service.UserService;
import com.HuangTaiQi.www.utils.DBUtil;
import com.HuangTaiQi.www.utils.code.Md5Utils;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用者的id
 * @author 14629
 */
public class UserServiceImpl implements UserService {

    /**
     * 新增user
     * @param username 账号
     * @param password 密码
     * @param name 名称
     * @param studentNumber 学号
     * @param electromobileModel 电动车型号
     * @param electromobileNumber 电动车牌号
     * @return 返回是否添加成功
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public boolean addUser(String username, String password, String name, String studentNumber, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException, NoSuchAlgorithmException {
        UserEntity user=new UserEntity();
        user.setElectromobileModel(electromobileModel);
        user.setName(name);
        user.setElectromobileNumber(electromobileNumber);
        user.setPassword(Md5Utils.encode(password));
        user.setUsername(username);
        user.setStudentNumber(studentNumber);
        user.setState(UserEntity.FORBID);
        user.setAuthorityId(1);
        DBUtil.beginTransaction();
        try {
            UserDaoImpl userDaoImpl = new UserDaoImpl();
            //还可以加studentNumber，学号唯一之类的。
            UserEntity userByUsername = userDaoImpl.getUserByUsername(username);
            if(userByUsername==null){
                userDaoImpl.addUser(user);
            }else {
                return false;
            }
        } catch (Exception e) {
            DBUtil.rollbackTransaction();
            DBUtil.close();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
        return true;
    }

    /**
     * 登录
     * @param username 账号
     * @param password 密码
     * @return 成功返回user对象，失败返回null
     * @throws Exception 异常
     */
    public UserEntity login(String username, String password) throws Exception {
        UserEntity userByUsernameAndPassword = new UserDaoImpl().getUserByUsernameAndPassword(username, Md5Utils.encode(password));
        DBUtil.close();
        if( userByUsernameAndPassword != null && userByUsernameAndPassword.getState()!=0){
            return userByUsernameAndPassword;
        }else {
            return null;
        }
    }

    /**
     * 展示未审核通过的user
     * @return 返回集合
     * @throws Exception 异常
     */
    public List<UserEntity> showAuditUser() throws Exception {
        List<UserEntity> usersWhereStateZero = new UserDaoImpl().getUsersByState(0);
        DBUtil.close();
        return usersWhereStateZero;
    }

    /**
     * 将用户的状态设值。用于审核通过用户
     * @param UserId 通过的用户id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void pass(int UserId,int state) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            new UserDaoImpl().setUserState(UserId,state);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 展示审核通过的用户
     * @return 返回审核通过的用户
     * @throws Exception 异常
     */
    public List<UserEntity> showAuditedUser() throws Exception {
        List<UserEntity> usersWhereStateOne = new UserDaoImpl().getUsersByState(1);
        DBUtil.close();
        return usersWhereStateOne;
    }

    /**
     * 删除用户
     * @param id 被删除用户的id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteById(int id) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            new UserDaoImpl().deleteUserById(id);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 设置权限
     * @param id 用户的id
     * @param authorityId 权限等级
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setAuthority(int id, int authorityId) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            new UserDaoImpl().setUserAuthorityId(id,authorityId);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 修改电动车的型号和牌号，修改后要重新审核
     * @param id 用户的id
     * @param electromobileModel 型号
     * @param electromobileNumber 牌号
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void alterMobile(Integer id, String electromobileModel, String electromobileNumber) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            userDaoImpl.alterMobile(id,electromobileModel,electromobileNumber);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }
}
