package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.dao.ParkDao;
import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.utils.TransactionManager;
import com.HuangTaiQi.www.utils.pool.ConnectionPoolManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ParkService {
    private Connection connection;


    public List<ParkingLotEntity> getParkingLots() throws Exception {
        connection= ConnectionPoolManager.getConnection();
        List<ParkingLotEntity> parkLots = new ParkDao(connection).getParkLots();
        ConnectionPoolManager.closeConnection(connection);
        return parkLots;
    }

    public void addParkingLot(String location, String name) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new ParkDao(connection).addParkingLot(location,name);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public List<ParkingSpotEntity> getParkingSpots(int lotId) throws Exception {
        connection=ConnectionPoolManager.getConnection();
        return new ParkDao(connection).getParkSpots(lotId);
    }

    public void deleteSpot(int spotId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        new ParkDao(connection).deleteSpot(spotId);
    }

    public void setSpotState(int spotId, int state) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new ParkDao(connection).setSpotState(spotId,state);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void deleteLot(int lotId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        ParkDao parkDao = new ParkDao(connection);
        try {
            parkDao.deleteSpotByLocationId(lotId);
            parkDao.deleteLot(lotId);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void alterLot(int lotId, String location, String name) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        ParkDao parkDao = new ParkDao(connection);
        try {
            parkDao.alterLot(lotId,location,name);
        } catch (SQLException | InterruptedException e) {
            transactionManager.commit();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }
}
