package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.controller.AdminServlet;
import com.HuangTaiQi.www.controller.UserServlet;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.po.UserEntity;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {
    private final Logger logger=Logger.getLogger("com.HuangTaiQi.www.view.View");
    private final Scanner scanner=new Scanner(System.in);
    public void begin(){
        System.out.println("欢迎来到校园电动车管理系统");
        System.out.println("0注册，1登录,2管理员注册，3管理员登陆");
        int reOrLo = scanner.nextInt();
        if(reOrLo==0){
            register();
        }else if(reOrLo==1){
            login();
        } else if (reOrLo==2) {
            adminRegister();
        } else if (reOrLo==3) {
            adminLogin();
        } else {
            System.out.println("输入错误已退出");
        }
    }
    private void register(){
        System.out.println("请输入账号");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        System.out.println("请输入真实姓名");
        String name = scanner.next();
        System.out.println("请输入学号");
        String studentNumber = scanner.next();
        System.out.println("请输入电动车型号");
        String electromobileModel = scanner.next();
        System.out.println("请输入电动车牌号");
        String electromobileNumber = scanner.next();
        //传数据给后端
        boolean register = new UserServlet().register(username, password, name, studentNumber, electromobileModel, electromobileNumber);
        if(register){
            System.out.println("注册成功");
            login();
        }else{
            System.out.println("注册失败");
        }
    }
    private void adminRegister(){
        System.out.println("请输入管理员密钥");
        String key = scanner.next();
        AdminEntity admin = new AdminServlet().register(key);
        if(admin==null){
            System.out.println("注册失败");
        }else {
            System.out.println("注册成功");
            System.out.println("账号："+admin.getUsername());
            System.out.println("密码："+admin.getPassword());
            System.out.println("请记住相应的账号密码");
            adminLogin();
        }
    }
    private void login(){
        System.out.println("欢迎来到登录界面");
        System.out.println("请输入账号");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.nextLine();
        UserEntity user = new UserServlet().login(username, password);
        if(user!=null){
            System.out.println("登录成功");
            studentMenu(user);
        }else{
            System.out.println("密码错误或管理员为审批，请重新登录或耐心等待");
        }
    }
    private void adminLogin(){
        System.out.println("欢迎来到登录界面");
        System.out.println("请输入账号");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        AdminEntity admin = new AdminServlet().login(username, password);
        if(admin!=null){
            System.out.println("登录成功");
            adminMenu(admin);
        }else{
            System.out.println("密码错误，请重新登录");
        }
    }
    private void studentMenu(UserEntity user){
        System.out.println("0立刻充电,1预约充电,2停车,3离开停车场，4修改电动车型号");
        switch (scanner.nextInt()){
            case 0:
                //立刻充电
                chargeNow();
                break;
            case 1:
                //预约充电
                reserveCharge();
                break;
            case 2:
                //停车
                park();
                break;
            case 3:
                //修改电动车型号
                modifyElectromobile();
                break;
            case 4:
                //离开停车场
                left();
            default:
                logger.log(Level.WARNING,"UNDEFINE INPUT ");
        }
        studentMenu(user);
    }

    private void left() {
        //设置停车位空闲
    }

    private void chargeNow() {
        //选择哪一个充电站。
        //显示出目前可以用的充电桩.
        //选择充电桩。显示出此充电桩的预约信息。
        //设置充电时长，开始充电。
    }

    private void park(){
        //选择停车场
        //显示出空闲的停车位
        //选择停车位
    }
    private void modifyElectromobile() {
        //显示当前电动车的型号和牌号
        //更改
        //state=0
    }

    private void reserveCharge() {
        //只能预约当天的充电
        //选择充电站
        //选择要预约的时间段
        //显示出空闲的充电桩
        //选择充电站
        //预约成功，请在预约时间内完成充电
    }

    private void adminMenu(AdminEntity admin){
        //审批用户
        audit();
        //查看用户
        showUser();
        //查看充电
        showCharge();
        //查看停车
        showPark();
        //查看报表

    }
    private void audit(){
        UserServlet userServlet = new UserServlet();
        for (UserEntity user : userServlet.showAuditUser()) {
            System.out.println(user);
        }
        //显示出state=0的用户信息
       while(true){
           System.out.println("请输入审核通过的用户id,输入0退出");
           int id = scanner.nextInt();
           if(id==0){
               break;
           }else{
               userServlet.pass(id);
               System.out.println("success");
           }
       }
        //通过id更改state
    }
    private void showUser(){
        //显示出所有state=1的用户信息。
        UserServlet userServlet = new UserServlet();
        for (UserEntity user : userServlet.showAuditedUser()) {
            System.out.println(user);
        }
        //通过id查看修改权限
        while(true){
            System.out.println("请输入想要修改的用户id,输入0退出");
            int id = scanner.nextInt();
            if(id==0){
                break;
            }else{
                System.out.println("1注销改账号，2封禁改账号（需要重新审核）");
                int deleteOrForbid=scanner.nextInt();
                if(deleteOrForbid==1||deleteOrForbid==2){
                    userServlet.alter(id,deleteOrForbid);
                    System.out.println("success");
                }else {
                    System.out.println("输入错误");
                }
            }
        }
    }
    private void showCharge(){
        //查看所有充电站
        //修改充电站信息，早上开启时间？，晚上关闭时间？
        //查看充电桩 增加减少充电桩，
    }
    private void showPark(){

    }
}
