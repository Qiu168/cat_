package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;

import java.sql.SQLException;
import java.util.List;

public interface ParkService {
    /**
     * 获取所有的停车场
     * @return 所有停车场集合
     * @throws Exception 异常
     */
    public List<ParkingLotEntity> getParkingLots() throws Exception;
    /**
     * 新增停车场
     * @param location 地点
     * @param name 名字
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void addParkingLot(String location, String name) throws SQLException, InterruptedException;
    /**
     * 获取停车场内的停车点
     * @param lotId 停车站id
     * @return 返回停车站中所有的停车点
     * @throws Exception 异常
     */
    public List<ParkingSpotEntity> getParkingSpots(int lotId) throws Exception;
    /**
     * 删除停车点
     * @param spotId 停车点id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */

    public void deleteSpot(int spotId) throws SQLException, InterruptedException;
    /**
     * 设置停车点的状态
     * @param spotId 停车点id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setSpotState(int spotId, int state) throws SQLException, InterruptedException;
    /**
     * 删除停车站及其所有停车点
     * @param lotId 停车站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteLot(int lotId) throws SQLException, InterruptedException;
    /**
     * 修改停车站
     * @param lotId 停车站id
     * @param location 地点
     * @param name 名称
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void alterLot(int lotId, String location, String name) throws SQLException, InterruptedException;
    /**
     * 新增停车点
     * @param lotId 所属的停车站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void addParkingSpot(int lotId) throws SQLException, InterruptedException;
    /**
     * 设置停车站的状态
     * 用于将某人使用的停车点设为空
     * @param userId 使用者的id
     * @param state 修改后的状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setSpotStateByState(Integer userId, int state) throws SQLException, InterruptedException;
}
