package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.controller.AdminServlet;
import com.HuangTaiQi.www.controller.UserServlet;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.regex.CommonRegex;

import java.util.Scanner;


/**
 * 初始页面
 * @author 14629
 */
public class IndexView {
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
        System.out.println("请输入账号，长度在6~18之间，只能包含字符、数字和下划线。");
        String username = scanner.next();
        System.out.println("请输入密码，长度在6~18之间。");
        String password = scanner.next();
        System.out.println("请输入真实姓名，只能输入中文");
        String name = scanner.next();
        System.out.println("请输入学号");
        String studentNumber = scanner.next();
        System.out.println("请输入电动车型号");
        String electromobileModel = scanner.next();
        System.out.println("请输入电动车牌号，长度为6 只能包含字母、数字。");
        String electromobileNumber = scanner.next();
        if(!(CommonRegex.check(CommonRegex.electromobileNumberRegex,electromobileNumber)&&CommonRegex.check(CommonRegex.usernameRegex,username)&&CommonRegex.check(CommonRegex.nameRegex,name))){
            System.out.println("输入不规范");
            return;
        }
        //传数据给后端
        boolean register = UserServlet.getInstance().register(username, password, name, studentNumber, electromobileModel, electromobileNumber);
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
        AdminEntity admin = AdminServlet.getInstance().register(key);
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
        String password = scanner.next();
        UserEntity user = UserServlet.getInstance().login(username, password);
        if(user!=null){
            System.out.println("登录成功");
            new MenuView().studentMenu(user);
        }else{
            System.out.println("密码错误或管理员未审批，请重新登录或耐心等待");
        }
    }
    private void adminLogin(){
        System.out.println("欢迎来到登录界面");
        System.out.println("请输入账号");
        String username = scanner.next();
        System.out.println("请输入密码");
        String password = scanner.next();
        AdminEntity admin = AdminServlet.getInstance().login(username, password);
        if(admin!=null){
            System.out.println("登录成功");
            new MenuView().adminMenu(admin);
        }else{
            System.out.println("密码错误，请重新登录");
        }
    }
}
