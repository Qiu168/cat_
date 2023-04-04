package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 停车
 * @author 14629
 */
public interface ParkDao {
    /**
     * 获取停车场
     * @return 停车场集合
     * @throws Exception 异常
     */
    List<ParkingLotEntity> getParkLots() throws Exception;

    /**
     * 新增停车场
     * @param location 地点
     * @param name 名字
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void addParkingLot(String location, String name) throws SQLException, InterruptedException;

    /**
     * 获取停车场内的停车点
     * @param lotId 停车场id
     * @return 此停车场中的所有停车点
     * @throws Exception 异常
     */
    List<ParkingSpotEntity> getParkSpots(int lotId) throws Exception;

    /**
     * 删除停车点
     * @param spotId 被删除的停车点id
     * @throws SQLException 异常
     */
    public void deleteSpot(int spotId) throws SQLException;

    /**
     * 设置停车点的状态，是否使用中
     * @param spotId 停车点id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setSpotState(int spotId, int state) throws SQLException, InterruptedException;

    /**
     * 删除停车场中的所有停车点
     * @param lotId 停车场id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteSpotByLocationId(int lotId) throws SQLException, InterruptedException;

    /**
     * 删除停车场
     * @param lotId 停车场id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteLot(int lotId) throws SQLException, InterruptedException;

    /**
     * 修改停车场
     * @param lotId 停车场id
     * @param location 停车场地点
     * @param name 名字
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void alterLot(int lotId, String location, String name) throws SQLException, InterruptedException;

    /**
     * 新增停车点
     * @param lotId 停车场id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     *
     */
    void addSpot(int lotId) throws SQLException, InterruptedException;

    /**
     * 通过停车点的状态设置停车点状态
     * @param userId 使用者的id，先前的状态
     * @param state 设置后的状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void setSpotStateByState(Integer userId, int state) throws SQLException, InterruptedException;
}
