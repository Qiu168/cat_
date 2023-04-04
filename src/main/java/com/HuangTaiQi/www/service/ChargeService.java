package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;

import java.sql.SQLException;
import java.util.List;

public interface ChargeService {
    /**
     * 获取所有的充电站
     * @return 所有的充电站
     * @throws Exception 异常
     */
    public List<ChargingStationEntity> getChargingStations() throws Exception;
    /**
     * 添加充电站
     * @param location 地点
     * @param name 名字
     * @param open 开始时间
     * @param close 关闭时间
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException;
    /**
     * 更新充电站的状态
     * @param stationId 充电站id
     * @param location 地点
     * @param name 名字
     * @param open 开始时间
     * @param close 关闭时间
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException;
    /**
     * 删除充电站
     * @param stationId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteChargingStationById(int stationId) throws SQLException, InterruptedException;
    /**
     * 获取充电桩
     * @param stationId 充电站id
     * @return 充电站内的充电桩
     * @throws Exception 异常
     */
    public List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception;
    /**
     * 充电站的状态是否能用
     * @param pileId id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setPileState(int pileId, int state) throws SQLException, InterruptedException;
    /**
     * 删除充电站
     * @param pileId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void deleteChargingPileById(int pileId) throws SQLException, InterruptedException;
    /**
     * 增加充电站
     * @param stationId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void addChargingPile(int stationId) throws SQLException, InterruptedException;
    /**
     * 获取空闲的充电桩
     * @param stationId 充电站id
     * @param hour 时间
     * @return 返回此时间空闲的充电桩
     * @throws Exception 异常
     */
    List<ChargingPileBean> getFreePile(int stationId, int hour) throws Exception;
    /**
     * 设置使用时间
     * @param id 充电桩的id
     * @param pile 充电桩
     * @param hour 开始使用的时间点
     * @param useTime 使用的时间段
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void setPileTime(Integer id, ChargingPileBean pile, int hour, int useTime) throws SQLException, InterruptedException;
}
