package com.HuangTaiQi.www.view;


import com.HuangTaiQi.www.controller.UserServlet;
import com.HuangTaiQi.www.po.*;

import java.util.*;


/**
 * @author 14629
 */
public class UserView {
    private final Scanner scanner=new Scanner(System.in);
    private final UserServlet userServlet = UserServlet.getInstance();
    public void modifyElectromobile(Integer id) {
        System.out.println("更改电动车后要重新审核 0退出，1继续");
        int input=scanner.nextInt();
        if(input==1){
            System.out.println("请输入更改后电动车的型号");
            String electromobileModel=scanner.next();
            System.out.println("请输入更改后电动车的牌号");
            String electromobileNumber=scanner.next();
            UserServlet.getInstance().alterMobile(id,electromobileModel,electromobileNumber);
            System.out.println("成功更新");
        }
        //显示当前电动车的型号和牌号
        //更改
        //state=0
    }
    public void showUser(){
        //显示出所有state>0的用户信息。

        for (UserEntity user : userServlet.showAuditedUser()) {
            System.out.println(user);
        }
        System.out.println("1修改用户的状态，2修改用户的权限");
        //通过id查看修改权限
        int input=scanner.nextInt();
        if(input==1){
            while(true){
                System.out.println("请输入想要修改状态的用户id,输入0退出");
                int id = scanner.nextInt();
                if(id==0){
                    break;
                }else{
                    System.out.println("1注销该账号(账号的消失)，2封禁该账号（需要重新审核）");
                    int deleteOrForbid=scanner.nextInt();
                    if(deleteOrForbid==1||deleteOrForbid==2){
                        userServlet.alterState(id,deleteOrForbid);
                        System.out.println("success");
                    }else {
                        System.out.println("输入错误");
                    }
                }
            }
        }else if(input==2) {
            while(true){
                System.out.println("请输入想要修改权限的用户id,输入0退出");
                int id = scanner.nextInt();
                if(id==0){
                    System.out.println("已退出");
                    break;
                }else{
                    System.out.println("当前有4种权限状态，1可以使用使用充电和停车，2只可以充电，3只可以停车，4什么都不能做\n" +
                            "请输入想要改变的权限的相应id（1-4）");
                    int authorityId = scanner.nextInt();
                    if(authorityId>0&&authorityId<5){
                        userServlet.alterAuthority(id,authorityId);
                        System.out.println("已修改");
                    }else {
                        System.out.println("输入错误");
                    }
                }
            }
        }
    }

}
