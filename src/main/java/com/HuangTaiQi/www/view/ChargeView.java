package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.controller.ChargeServlet;
import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.TextLog;

import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChargeView {
    private final Logger logger=Logger.getLogger(MenuView.class.getName());
    private final Scanner scanner=new Scanner(System.in);
    public void chargeAtTime(UserEntity user, int hour, int minute) {
        ChargeServlet chargeServlet=new ChargeServlet();
        List<ChargingStationEntity> chargingStationEntities = chargeServlet.showChargingStation();
        Map<Integer,ChargingStationEntity> map=new HashMap<>();
        for (ChargingStationEntity chargingStationEntity : chargingStationEntities) {
            System.out.println(chargingStationEntity);
            map.put(chargingStationEntity.getId(),chargingStationEntity);
        }
        Map<Integer, ChargingPileBean> freePiles =new HashMap<>();
        //选择哪一个充电站。
        System.out.println("请输入你选择哪一个充电站");
        int stationId=scanner.nextInt();
        if(map.containsKey(stationId)){
            ChargingStationEntity chargingStation = map.get(stationId);
            if(chargingStation.getOpen()>hour||chargingStation.getClose()<hour){
                System.out.println("此时间充电站未开放");
                System.out.println("此充电站的开放时间为"+chargingStation.getOpen()+"~"+chargingStation.getClose());
                return;
            }
            System.out.println("下面是此充电站当前时间所有可用的充电桩");
            List<ChargingPileBean> chargingPileBeans = chargeServlet.showFreePile(stationId, hour);
            for (ChargingPileBean chargingPileBean : chargingPileBeans) {
                System.out.println(chargingPileBean.toMyString(minute));
                freePiles.put(chargingPileBean.getId(),chargingPileBean);
            }
            System.out.println("请输入想要使用的充电桩id");
            int pileId=scanner.nextInt();
            System.out.println("请输入你想用到几点？(24小时制)");
            int useTime=scanner.nextInt();
            if(freePiles.containsKey(pileId)&&freePiles.get(pileId).getFreeTime()>=useTime){
                chargeServlet.usePile(user.getId(),freePiles.get(pileId),hour,useTime);
                System.out.println("请在指定时间内完成充电");
                LocalDateTime now = LocalDateTime.now();
                TextLog.add(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()+
                        "id为"+user.getId()+"的用户在id为"+pileId+"的充电桩充电"+useTime+"小时\n");
            }else {
                System.out.println("输入错误");
            }

        }else {
            System.out.println("输入错误");
        }
        //显示出目前可以用的充电桩.
        //选择充电桩。显示出此充电桩的预约信息。
        //设置充电时长，开始充电。
    }
    public void showCharge(){
        ChargeServlet chargeServlet = new ChargeServlet();
        List<Integer> stationIds = showChargingStation(chargeServlet);
        System.out.println("你接下来想做？1修改充电站信息，2删除充电站信息，3添加充电站，4查看/修改/删除充电站内的充电桩,5添加充电桩");
        switch (scanner.nextInt()){
            case 1:
                alterStation(stationIds,chargeServlet);
                break;
            case 2:
                deleteStation(stationIds,chargeServlet);
                break;
            case 3:
                addStation(chargeServlet);
                break;
            case 4:
                showChargingPile(stationIds,chargeServlet);
                break;
            case 5:
                addPile(stationIds,chargeServlet);
                break;
            default:
                logger.log(Level.WARNING,"UNDEFINE INPUT ");
        }
        //修改充电站，查看充电桩内的充电桩

        //修改充电站信息，早上开启时间？，晚上关闭时间？
        //查看充电桩 增加减少充电桩，
    }

    public List<Integer> showChargingStation(ChargeServlet chargeServlet){
        List<Integer> stationIds =new ArrayList<>();
        //查看所有充电站
        for (ChargingStationEntity chargingStation : chargeServlet.showChargingStation()) {
            System.out.println(chargingStation);
            stationIds.add(chargingStation.getId());
        }
        return stationIds;
    }
    public void addPile(List<Integer> stationIds, ChargeServlet chargeServlet) {
        System.out.println("请输入想要添加到的充电站的id,0退出");
        int stationId = scanner.nextInt();
        if(stationId==0){
            System.out.println("已退出");
        }else if(stationIds.contains(stationId)) {
            chargeServlet.addChargingPile(stationId);
            System.out.println("添加成功");
            TextLog.add("在id为"+stationId+"的充电站新增一个充电桩");
        } else {
            logger.log(Level.WARNING,"UNDEFINE INPUT ");
        }
    }

    public void showChargingPile(List<Integer> stationIds, ChargeServlet chargeServlet) {
        List<Integer> pileIds =new ArrayList<>();
        System.out.println("请输入想查看的充电站id");
        int stationId = scanner.nextInt();
        if(stationIds.contains(stationId)){
            System.out.println("下面是此充电站所有的充电桩");
            for (ChargingPileEntity chargingPile : chargeServlet.showChargingPiles(stationId)) {
                System.out.println(chargingPile);
                pileIds.add(chargingPile.getId());
            }
            System.out.println("输入想要修改的充电桩id，或者输入0退出");
            int pileId = scanner.nextInt();
            if(pileId==0){
                System.out.println("已退出");
            }else if(pileIds.contains(pileId)){
                System.out.println("0退出，1设置充电桩能否使用，2删除充电桩");
                int input = scanner.nextInt();
                if(input==1){
                    System.out.println("输入-1将其禁用，0将其解禁");
                    input = scanner.nextInt();
                    if(input==-1||input==0){
                        chargeServlet.setPileState(pileId,input);
                    }else {
                        System.out.println("?你输错了");
                    }
                    //
                }else if(input==2){
                    chargeServlet.deleteChargingPile(pileId);
                }
            }else{
                System.out.println("?你输错了");
            }
        }else{
            System.out.println("?你输错了");
        }
    }

    public void addStation(ChargeServlet chargeServlet) {
        System.out.println("你确定要添加充电站吗？输入1添加");
        if(scanner.nextInt()==1){
            System.out.println("请输入新增充电站的地点后回车");
            String location=scanner.nextLine();
            System.out.println("请输入新增充电站的名称");
            String name=scanner.next();
            System.out.println("请输入充电站每天的开启时间");
            int open=scanner.nextInt();
            System.out.println("请输入充电站每天的关闭时间");
            int close=scanner.nextInt();
            chargeServlet.addChargingStation(location,name,open,close);
        }else {
            System.out.println("已取消");
        }
    }

    public void deleteStation(List<Integer> stationIds, ChargeServlet chargeServlet) {
        System.out.println("删除充电站将会把在此充电站的所有充电桩删除！是否继续？0退出，1继续");
        if(scanner.nextInt()==1){
            System.out.println("请输入你要删除的充电站的id");
            int stationId = scanner.nextInt();
            if(stationIds.contains(stationId)){
                chargeServlet.deleteChargingStation(stationId);
                TextLog.add("删除了id为"+stationId+"的充电站");
            }else{
                System.out.println("?你输错了");
            }
        }
    }

    public void alterStation(List<Integer> stationIds, ChargeServlet chargeServlet) {
        System.out.println("请输入想要修改的充电站id");
        int stationId = scanner.nextInt();
        if(stationIds.contains(stationId)){
            System.out.println("请输入修改后充电站的地点后回车");
            String location=scanner.nextLine();
            System.out.println("请输入修改后充电站的名称");
            String name=scanner.next();
            System.out.println("请输入充电站每天的开启时间");
            int open=scanner.nextInt();
            System.out.println("请输入充电站每天的关闭时间");
            int close=scanner.nextInt();
            chargeServlet.updateChargingStation(stationId,location,name,open,close);
        }else{
            System.out.println("?你输错了");
        }
    }

}
