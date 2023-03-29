package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.service.ChargeService;

import java.sql.SQLException;
import java.util.List;

public class ChargeServlet extends BaseServlet{
    ChargeService chargeService=new ChargeService();
    public List<ChargingStationEntity> showChargingStation() {
        try {
            return new ChargeService().getChargingStations();
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }

    public void updateChargingStation(int stationId, String location, String name, int open, int close) {
        try {
            chargeService.updateChargingStation(stationId,location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class, e);
        }
    }

    public void deleteChargingStation(int stationId) {
        try {
            chargeService.deleteChargingStationById(stationId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void addChargingStation(String location, String name, int open, int close) {
        try {
            chargeService.addChargingStation(location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public List<ChargingPileEntity> showChargingPiles(int stationId) {
        try {
            return chargeService.getChargingPilesByStationId(stationId);
        } catch (Exception e) {
            handleException(ChargeServlet.class,e);
        }
        return null;
    }

    public void setPileState(int pileId, int state)  {
        try {
            chargeService.setPileState(pileId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void deleteChargingPile(int pileId) {
        try {
            chargeService.deleteChargingPileById(pileId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }

    public void addChargingPile(int stationId) {
        try {
            chargeService.addChargingPile(stationId);
        } catch (SQLException | InterruptedException e) {
            handleException(ChargeServlet.class,e);
        }
    }
}
