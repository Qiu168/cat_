package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.controller.AdminServlet;
import com.HuangTaiQi.www.controller.UserServlet;
import com.HuangTaiQi.www.po.AdminEntity;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.TextLog;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    private final Scanner scanner=new Scanner(System.in);
    public void changePassword(AdminEntity admin) {
        System.out.println("请输入原密码");
        String origin=scanner.next();
        System.out.println("请输入更改之后的密码");
        boolean changePassword = new AdminServlet().ChangePassword(admin.getId(), origin, scanner.next());
        if(changePassword){
            System.out.println("更改成功");
        }else {
            System.out.println("原密码错误");
        }
    }
    public void audit(AdminEntity admin){
        UserServlet userServlet = new UserServlet();
        List<UserEntity> userEntities = userServlet.showAuditUser();
        if(userEntities==null){
            System.out.println("没有需要审核的用户");
            return;
        }
        for (UserEntity user : userEntities) {
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
                LocalDateTime now = LocalDateTime.now();
                TextLog.add(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()+"id为"+admin.getId() +
                        "的管理员审核通过了id为"+id+"的用户\n");
            }
        }
        //通过id更改state
    }
}
