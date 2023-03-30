package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.service.ParkService;

import java.sql.SQLException;
import java.util.List;

public class ParkServlet extends BaseServlet{
    private final ParkService parkService=new ParkService();
    public List<ParkingLotEntity> showParkingLot() {
        try {
            return parkService.getParkingLots();
        } catch (Exception e) {
            handleException(ParkServlet.class,e);
        }
        return null;
    }

    public Boolean addParkingLot(String location, String name) {
        try {
            parkService.addParkingLot(location,name);
            return true;
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
        return false;
    }

    public List<ParkingSpotEntity> showParkingSpot(int lotId) {
        try {
            return parkService.getParkingSpots(lotId);
        } catch (Exception e) {
            handleException(ParkServlet.class,e);
        }
        return null;
    }

    public void deleteSpot(int spotId) {
        try {
            parkService.deleteSpot(spotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void setSpotState(int spotId, int state) {
        try {
            parkService.setSpotState(spotId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void deleteLot(int lotId) {
        try {
            parkService.deleteLot(lotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void alterLot(int lotId, String location, String name) {
        try {
            parkService.alterLot(lotId,location,name);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void addParkingSpot(int lotId) {
        try {
            parkService.addParkingSpot(lotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void setSpotStateByState(Integer userId, int state) {
        try {
            parkService.setSpotStateByState(userId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }
}
