package com.HuangTaiQi.www.view;

import com.HuangTaiQi.www.po.*;

public class BaseView {
    public static String showUser(UserEntity user) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("userId:").append(user.getId())
                .append("  username:").append(user.getUsername())
                .append("  name:").append(user.getName())
                .append("   userAuthority:").append(user.getAuthorityId())
                .append("    userElectromobileModel:").append(user.getElectromobileModel())
                .append("    userElectromobileNumber:").append(user.getElectromobileNumber())
                .append("   userStudentNumber:").append(user.getStudentNumber());
        return stringBuilder.toString();
    }
    public static String showParkingLotEntity(ParkingLotEntity parkingLotEntity){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id:").append(parkingLotEntity.getId())
                .append("  地点:").append(parkingLotEntity.getLocation())
                .append("  停车场名:").append(parkingLotEntity.getName());
        return stringBuilder.toString();
    }
    public static String showParkingSpotEntity(ParkingSpotEntity parkingSpotEntity){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id:").append(parkingSpotEntity.getId())
                .append(" 状态：").append(parkingSpotEntity.getState())
                .append(" 所属停车场的id").append(parkingSpotEntity.getLocationId());
        return stringBuilder.toString();
    }

    public static String showPile(ChargingPileEntity pileById) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("id:").append(pileById.getId())
                .append(" 状态：").append(pileById.getState())
                .append(" 所属充电站的id").append(pileById.getStationId());
        return stringBuilder.toString();

    }

    public static String showComment(CommentEntity commentEntity) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder
                .append(" 评论：").append(commentEntity.getContent());
        return stringBuilder.toString();
    }
}
