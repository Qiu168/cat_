package com.HuangTaiQi.www.service.impl;

import com.HuangTaiQi.www.dao.impl.ChargeDaoImpl;
import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.service.ChargeService;
import com.HuangTaiQi.www.utils.DBUtil;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 充电服务层
 * @author 14629
 */
public class ChargeServiceImpl implements ChargeService {
    private final ChargeDaoImpl chargeDao =  ChargeDaoImpl.getInstance();

    /**
     * 获取所有的充电站
     * @return 所有的充电站
     * @throws Exception 异常
     */
    public List<ChargingStationEntity> getChargingStations() throws Exception {
        List<ChargingStationEntity> chargingStations = chargeDao.getChargingStations();
        DBUtil.close();
        return chargingStations;
    }

    /**
     * 添加充电站
     * @param location 地点
     * @param name 名字
     * @param open 开始时间
     * @param close 关闭时间
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.addChargingStation(location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();

    }

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
    public void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.updateChargingStation(stationId,location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();

    }

    /**
     * 删除充电站
     * @param stationId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteChargingStationById(int stationId) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.deleteChargingStationById(stationId);
            chargeDao.deleteChargingPileByStationId(stationId);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 获取充电桩
     * @param stationId 充电站id
     * @return 充电站内的充电桩
     * @throws Exception 异常
     */

    public List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception {
        List<ChargingPileEntity> chargingPilesByStationId = chargeDao.getChargingPilesByStationId(stationId);
        DBUtil.close();
        return chargingPilesByStationId;
    }

    /**
     * 充电站的状态是否能用
     * @param pileId id
     * @param state 状态
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void setPileState(int pileId, int state) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.setPileState(pileId,state);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 删除充电站
     * @param pileId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void deleteChargingPileById(int pileId) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.deleteChargingPileById(pileId);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 增加充电站
     * @param stationId 充电站id
     * @throws SQLException 异常
     * @throws InterruptedException 异常
     */
    public void addChargingPile(int stationId) throws SQLException, InterruptedException {
        DBUtil.beginTransaction();
        try {
            chargeDao.addChargingPile(stationId);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
    }

    /**
     * 获取空闲的充电桩
     * @param stationId 充电站id
     * @param hour 时间
     * @return 返回此时间空闲的充电桩
     * @throws Exception 异常
     */

    public List<ChargingPileBean> getFreePile(int stationId, int hour) throws Exception {
        List<ChargingPileBean> piles=new ArrayList<>();
        List<ChargingPileEntity> chargingPilesByStationId = chargeDao.getChargingPilesByStationId(stationId);
        if(chargingPilesByStationId==null){
            return null;
        }
        for (ChargingPileEntity chargingPileEntity : chargingPilesByStationId) {
            int freeTime = pileTime(hour, chargingPileEntity);
            if(freeTime>0){
                ChargingPileBean chargingPileBean=new ChargingPileBean(chargingPileEntity);
                chargingPileBean.setFreeTime(freeTime);
                piles.add(chargingPileBean);
            }
        }
        return piles;
    }

    /**
     * 充电桩在某一时间后可以持续使用多久
     * @param hour 某一时间
     * @param chargingPileEntity 充电桩
     * @return 返回可以使用多久时间
     */
    private int pileTime(int hour, ChargingPileEntity chargingPileEntity) {
        ChargingPileBean chargingPileBean=new ChargingPileBean(chargingPileEntity);
        List<Integer> pileSituation = chargingPileBean.getPileSituation();
        int freeTime=0;
        while(pileSituation.get(hour - 6)==0&&hour<23){
            freeTime++;
            hour++;
        }
        return freeTime;
    }

    /**
     * 设置使用时间
     *
     * @param id      充电桩的id
     * @param pile    充电桩
     * @param hour    开始使用的时间点
     * @param useTime 使用的时间段
     * @return 是否成功
     * @throws SQLException 异常
     */
    public boolean setPileTime(Integer id, ChargingPileBean pile, int hour, int useTime) throws SQLException {
        DBUtil.beginTransaction();
        List<Integer> pileSituation = pile.getPileSituation();
        for (int i = 0; i < useTime; i++) {
            pileSituation.set(hour-6+i,id);
        }
        pile.setPileSituation(pileSituation);
        try {
            chargeDao.setPileTime(pile);
        } catch (SQLException | InterruptedException e) {
            DBUtil.rollbackTransaction();
            throw new RuntimeException(e);
        }
        DBUtil.commitTransaction();
        DBUtil.close();
        return true;
    }

    public ChargingPileEntity getChargingPilesById(int pileId) throws Exception {
        ChargingPileEntity pileById = chargeDao.getPileById(pileId);
        DBUtil.close();
        return pileById;
    }
}
