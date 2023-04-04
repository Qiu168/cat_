package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.controller.ParkServlet;
import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.po.UserEntity;
import com.HuangTaiQi.www.utils.TextLog;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkView {
    private final Logger logger=Logger.getLogger(MenuView.class.getName());
    private final Scanner scanner=new Scanner(System.in);
    public void park(UserEntity user){
        ParkServlet parkServlet=new ParkServlet();
        //选择停车场
        List<ParkingLotEntity> parkingLotEntities = parkServlet.showParkingLot();
        List<Integer> lotIds=new ArrayList<>();
        if(parkingLotEntities==null){
            System.out.println("暂无停车场");
        }else {
            for (ParkingLotEntity parkingLotEntity : parkingLotEntities) {
                System.out.println(parkingLotEntity);
                lotIds.add(parkingLotEntity.getId());
            }
            System.out.println("请输入你选择的停车场id");
            int lotId=scanner.nextInt();
            if(lotIds.contains(lotId)){
                List<ParkingSpotEntity> parkingSpotEntities = parkServlet.showParkingSpot(lotId);
                List<Integer> spotIds=new ArrayList<>();
                if(parkingSpotEntities==null){
                    System.out.println("此停车场没有停车位哦");
                }else {
                    System.out.println("下列是此停车场的空闲停车位");
                    for (ParkingSpotEntity parkingSpotEntity : parkingSpotEntities) {
                        if(parkingSpotEntity.getState()==0){
                            System.out.println(parkingSpotEntity);
                            spotIds.add(parkingSpotEntity.getId());
                        }
                    }
                    if(spotIds.isEmpty()){
                        System.out.println("此停车场已满，请选择其他停车场或等待");
                    }else{
                        System.out.println("请输入你选择的停车位id");
                        int spotId=scanner.nextInt();
                        if(spotIds.contains(spotId)){
                            parkServlet.setSpotState(spotId,user.getId());
                            //感觉这里可以加一个停入时间，这样管理员就可看到已经停了多久然后管理不动的车
                            System.out.println("成功停车，离开时记得使用离开选项");
                            LocalDateTime now = LocalDateTime.now();
                            TextLog.add(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()+
                                    "id为"+user.getId()+"的用户停入了id为"+spotId+"的停车位\n");
                        }else{
                            System.out.println("输入错误");
                        }
                    }
                }
            }
        }
        //显示出空闲的停车位
        //选择停车位
    }
    public void left(UserEntity user) {
        ParkServlet parkServlet=new ParkServlet();
        parkServlet.setSpotStateByState(user.getId(),0);
        System.out.println("已离开停车位");
        LocalDateTime now = LocalDateTime.now();
        TextLog.add(now.getYear() + "-" + now.getMonthValue() + "-" + now.getDayOfMonth()+
                "id为"+user.getId()+"的用户离开了停车位\n");
    }
    public void showPark(){
        System.out.println("下面展示所有停车场");
        ParkServlet parkServlet=new ParkServlet();
        List<ParkingLotEntity> parkingLotEntities = parkServlet.showParkingLot();
        List<Integer> lotIds=new ArrayList<>();
        if(parkingLotEntities==null){
            System.out.println("暂无停车场");
        }else {
            for (ParkingLotEntity parkingLotEntity : parkingLotEntities) {
                System.out.println(BaseView.showParkingLotEntity(parkingLotEntity));
                lotIds.add(parkingLotEntity.getId());
            }
            System.out.println("1新增停车场，2修改停车场，3删除停车场，4查看停车场内的停车位");
            switch (scanner.nextInt()){
                case 1:
                    addLot(parkServlet);
                    break;
                case 2:
                    alterLot(lotIds,parkServlet);
                    break;
                case 3:
                    deleteLot(lotIds,parkServlet);
                    break;
                case 4:
                    showParkingSpot(lotIds,parkServlet);
                    break;
                default:
                    logger.log(Level.WARNING,"UNDEFINE INPUT ");
            }
        }
    }

    public void showParkingSpot(List<Integer> lotIds, ParkServlet parkServlet) {
        System.out.println("请输入你想查看的停车场id");
        int lotId= scanner.nextInt();
        if(lotIds.contains(lotId)){
            List<ParkingSpotEntity> parkingSpotEntities = parkServlet.showParkingSpot(lotId);
            List<Integer> spotIds=new ArrayList<>();
            if(parkingSpotEntities==null){
                System.out.println("此停车场还没有停车位哦");
                System.out.println("你可以 0退出 1新增停车位");
                if(scanner.nextInt()==1){
                    addSpot(lotId,parkServlet);
                    System.out.println("已添加");
                }
            }else{
                System.out.println("下列是此停车场的所有停车位");
                for (ParkingSpotEntity parkingSpotEntity : parkingSpotEntities) {
                    System.out.println(BaseView.showParkingSpotEntity(parkingSpotEntity));
                    spotIds.add(parkingSpotEntity.getId());
                }
                System.out.println("输入0退出，1删除停车位，2修改停车位状态");
                int input=scanner.nextInt();
                if(input==0){
                    System.out.println("已退出");
                } else if (input == 1) {
                    deleteSpot(spotIds,parkServlet);
                } else if (input == 2) {
                    alterSpot(spotIds,parkServlet);
                }
            }
        }else {
            System.out.println("输入错误");
        }
    }

    public void addSpot(int lotId, ParkServlet parkServlet) {
        parkServlet.addParkingSpot(lotId);
    }

    public void alterSpot(List<Integer> spotIds, ParkServlet parkServlet) {
        System.out.println("请输入你想修改的停车位id");
        int spotId = scanner.nextInt();
        if(spotIds.contains(spotId)){
            System.out.println("请输入改后的state，-1禁用，0可用");
            int state =scanner.nextInt();
            parkServlet.setSpotState(spotId,state);
        }
    }

    public void deleteSpot(List<Integer> spotIds, ParkServlet parkServlet) {
        System.out.println("请输入你想删除的充电桩id");
        int spotId = scanner.nextInt();
        if(spotIds.contains(spotId)){
            parkServlet.deleteSpot(spotId);
            System.out.println("删除成功");
        }else {
            System.out.println("输入错误");
        }
    }

    public void deleteLot(List<Integer> lotIds, ParkServlet parkServlet) {
        System.out.println("输入0退出或请输入想要删除的停车场id，注意！删除停车场会将停车场中所有停车位删除");
        int lotId=scanner.nextInt();
        if(lotId==0){
            System.out.println("已退出");
        } else if (lotIds.contains(lotId)) {
            parkServlet.deleteLot(lotId);
        }else{
            System.out.println("输入错误已退出");
        }
    }

    public void alterLot(List<Integer> lotIds, ParkServlet parkServlet) {
        System.out.println("请输入想要修改的停车场id或输入0退出");
        int lotId=scanner.nextInt();
        if(lotId==0){
            System.out.println("已退出");
        } else if (lotIds.contains(lotId)) {
            System.out.println("请输入修改后停车场的地点");
            String location=scanner.next();
            System.out.println("请输入修改后停车场的名字");
            String name = scanner.next();
            parkServlet.alterLot(lotId,location,name);
        }else{
            System.out.println("输入错误已退出");
        }
    }

    public void addLot(ParkServlet parkServlet) {
        System.out.println("请输入新增停车场的地点");
        String location = scanner.next();
        System.out.println("请输入新增停车场的名称");
        String name=scanner.next();
        if(parkServlet.addParkingLot(location, name)){
            System.out.println("已添加");
        }else{
            System.out.println("添加失败");
        }
    }
}
