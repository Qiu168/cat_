package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.po.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuView {
    private final Logger logger=Logger.getLogger(MenuView.class.getName());
    private final Scanner scanner=new Scanner(System.in);
    private final AdminView adminView=new AdminView();
    private final ChargeView chargeView=new ChargeView();
    private final ParkView parkView=new ParkView();
    private final UserView userView=new UserView();
    public void studentMenu(UserEntity user){
        LocalDateTime now = LocalDateTime.now();
        System.out.println("0立刻充电,1预约充电,2停车,3离开停车场，4修改电动车型号,5退出");
        switch (scanner.nextInt()){
            case 0:
                //立刻充电
                if(user.getAuthorityId()==AuthorityEntity.PARK||user.getAuthorityId()==AuthorityEntity.CANT_DO_EVERYTHING){
                    System.out.println("对不起您没有权限充电");
                    break;
                }
                System.out.println("当前时间为"+now.getHour()+" : "+now.getMinute());
                chargeView.chargeAtTime(user, now.getHour(),now.getMinute());
                break;
            case 1:
                //预约充电
                if(user.getAuthorityId()==AuthorityEntity.PARK||user.getAuthorityId()==AuthorityEntity.CANT_DO_EVERYTHING){
                    System.out.println("对不起您没有权限充电");
                    break;
                }
                System.out.println("当前时间为"+now.getHour()+" : "+now.getMinute());
                System.out.println("请输入你想预约的时间的小时（24小时制），注意只能输入>=现在时间：");
                int hour=scanner.nextInt();
                System.out.println("请输入你想预约的时间的分钟，注意只能输入>=现在时间：");
                int minute=scanner.nextInt();
                if(isValidTime(now, hour, minute)){
                    chargeView.chargeAtTime(user,hour,minute);
                }else{
                    System.out.println("输入不合法，已退出");
                }
                break;
            case 2:
                //停车
                if(user.getAuthorityId()==AuthorityEntity.CHARGE||user.getAuthorityId()==AuthorityEntity.CANT_DO_EVERYTHING){
                    System.out.println("对不起您没有权限停车");
                    break;
                }
                parkView.park(user);
                break;
            case 3:
                //修改电动车型号
                userView.modifyElectromobile(user.getId());
                break;
            case 4:
                //离开停车场
                if(user.getAuthorityId()==AuthorityEntity.CHARGE||user.getAuthorityId()==AuthorityEntity.CANT_DO_EVERYTHING){
                    System.out.println("对不起您没有权限停车");
                    break;
                }
                parkView.left(user);
                break;
            case 5:
                return;
            default:
                logger.log(Level.WARNING,"UNDEFINE INPUT ");
        }
        studentMenu(user);
    }

    private boolean isValidTime(LocalDateTime now, int hour, int minute) {
        if(hour<now.getHour()){
            return false;
        }else {
            return hour != now.getHour() || minute >= now.getMinute();
        }
    }

    public void adminMenu(AdminEntity admin){
        System.out.println("1审批用户，2查看已审批的用户，3查看充电，4查看停车，5查看报表，6改密码");
        switch (scanner.nextInt()){
            case 1:
                //审批用户
                adminView.audit(admin);
                break;
            case 2:
                //查看用户
                userView.showUser();
                break;
            case 3:
                //查看充电
                chargeView.showCharge();
                break;
            case 4:
                //查看停车
                parkView.showPark();
                break;
            case 5:
                //查看报表
                break;
            case 6:
                adminView.changePassword(admin);
                break;
            default:
                logger.log(Level.WARNING,"UNDEFINE INPUT ");
        }
    }

}
