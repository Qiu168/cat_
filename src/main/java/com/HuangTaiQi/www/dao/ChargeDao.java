package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 充电
 * @author 14629
 */
public interface ChargeDao {
    /**
     * 获取所有充电站
     * @return 充电站集合
     * @throws Exception 异常
     */
    List<ChargingStationEntity> getChargingStations() throws Exception;

    /**
     * 新增充电站
     * @param location 充电站地点
     * @param name 名称
     * @param open 开启时间
     * @param close 关闭时间
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */

    void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException;

    /**
     * 修改充电站的参数
     * @param stationId 充电站id
     * @param location 地点
     * @param name 名字
     * @param open 开启时间
     * @param close 关闭时间
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException;

    /**
     * 删除充电站
     * @param stationId 被删除的充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void deleteChargingStationById(int stationId) throws SQLException, InterruptedException;

    /**
     * 删除某个充电站所有的充电桩
     * @param stationId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void deleteChargingPileByStationId(int stationId) throws SQLException, InterruptedException;

    /**
     * 设置充电桩的状态
     * @param pileId 充电桩的id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void setPileState(int pileId, int state) throws SQLException, InterruptedException;

    /**
     * 删除充电桩
     * @param pileId 被删除的充电桩id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void deleteChargingPileById(int pileId) throws SQLException, InterruptedException;

    /**
     * 新增充电桩
     * @param stationId 新增到的充电站
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    void addChargingPile(int stationId) throws SQLException, InterruptedException;

    /**
     * 设施充电桩的使用时间。占位表示此时间有人使用
     *
     * @param pile 充电站
     * @throws SQLException         异常
     * @throws InterruptedException 异常
     */
     void setPileTime(ChargingPileBean pile) throws SQLException, InterruptedException ;

    /**
     * 得到充电站里所有的充电桩
     * @param stationId 充电站id
     * @return 充电站id
     * @throws Exception 异常
     */
    List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception;

    /**
     * 通过id查询pile
     * @param pileId id
     * @return 充电桩对象
     * @throws Exception 异常
     */
    ChargingPileEntity getPileById(int pileId) throws Exception;


}
