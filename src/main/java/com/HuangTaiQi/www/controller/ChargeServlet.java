package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.service.impl.ChargeServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 14629
 */
public class ChargeServlet extends BaseServlet{
    ChargeServiceImpl chargeServiceImpl =new ChargeServiceImpl();
    public List<ChargingStationEntity> showChargingStation() {
        try {
            return new ChargeServiceImpl().getChargingStations();
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }

    public void updateChargingStation(int stationId, String location, String name, int open, int close) {
        try {
            chargeServiceImpl.updateChargingStation(stationId,location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class, e);
        }
    }

    public void deleteChargingStation(int stationId) {
        try {
            chargeServiceImpl.deleteChargingStationById(stationId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void addChargingStation(String location, String name, int open, int close) {
        try {
            chargeServiceImpl.addChargingStation(location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public List<ChargingPileEntity> showChargingPiles(int stationId) {
        try {
            return chargeServiceImpl.getChargingPilesByStationId(stationId);
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }

    public void setPileState(int pileId, int state)  {
        try {
            chargeServiceImpl.setPileState(pileId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void deleteChargingPile(int pileId) {
        try {
            chargeServiceImpl.deleteChargingPileById(pileId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void addChargingPile(int stationId) {
        try {
            chargeServiceImpl.addChargingPile(stationId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    /**
     * 某时间段可以使用的充电桩
     * @param stationId 充电站id
     * @param hour hour
     */
    public List<ChargingPileBean> showFreePile(int stationId, int hour) {
        try {
            return chargeServiceImpl.getFreePile(stationId,hour);
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }


    public boolean usePile(Integer id, ChargingPileBean pile, int hour, int useTime) {
        try {
            return chargeServiceImpl.setPileTime(id,pile,hour,useTime);
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return false;
    }

    public ChargingPileEntity getPileById(int pileId)  {
        try {
            return chargeServiceImpl.getChargingPilesById(pileId);
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }
}
