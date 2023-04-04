package com.HuangTaiQi.www.controller;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.service.impl.ParkServiceImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * @author 14629
 */
public class ParkServlet extends BaseServlet{
    private final ParkServiceImpl parkServiceImpl =new ParkServiceImpl();
    public List<ParkingLotEntity> showParkingLot() {
        try {
            return parkServiceImpl.getParkingLots();
        } catch (Exception e) {
            handleException(ParkServlet.class,e);
        }
        return null;
    }

    public Boolean addParkingLot(String location, String name) {
        try {
            parkServiceImpl.addParkingLot(location,name);
            return true;
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
        return false;
    }

    public List<ParkingSpotEntity> showParkingSpot(int lotId) {
        try {
            return parkServiceImpl.getParkingSpots(lotId);
        } catch (Exception e) {
            handleException(ParkServlet.class,e);
        }
        return null;
    }

    public void deleteSpot(int spotId) {
        try {
            parkServiceImpl.deleteSpot(spotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void setSpotState(int spotId, int state) {
        try {
            parkServiceImpl.setSpotState(spotId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void deleteLot(int lotId) {
        try {
            parkServiceImpl.deleteLot(lotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void alterLot(int lotId, String location, String name) {
        try {
            parkServiceImpl.alterLot(lotId,location,name);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void addParkingSpot(int lotId) {
        try {
            parkServiceImpl.addParkingSpot(lotId);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }

    public void setSpotStateByState(Integer userId, int state) {
        try {
            parkServiceImpl.setSpotStateByState(userId,state);
        } catch (SQLException | InterruptedException e) {
            handleException(ParkServlet.class,e);
        }
    }
}
