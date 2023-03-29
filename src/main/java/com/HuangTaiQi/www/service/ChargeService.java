package com.HuangTaiQi.www.service;

import com.HuangTaiQi.www.dao.ChargeDao;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.utils.pool.ConnectionPoolManager;
import com.HuangTaiQi.www.utils.TransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChargeService {
    private Connection connection;
    public List<ChargingStationEntity> getChargingStations() throws Exception {
        connection= ConnectionPoolManager.getConnection();
        List<ChargingStationEntity> chargingStations = new ChargeDao(connection).getChargingStations();
        ConnectionPoolManager.closeConnection(connection);
        return chargingStations;
    }


    public void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new ChargeDao(connection).addChargingStation(location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new ChargeDao(connection).updateChargingStation(stationId,location,name,open,close);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void deleteChargingStationById(int stationId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        ChargeDao chargeDao = new ChargeDao(connection);
        try {
            chargeDao.deleteChargingStationById(stationId);
            chargeDao.deleteChargingPileByStationId(stationId);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception {
        connection=ConnectionPoolManager.getConnection();
        List<ChargingPileEntity> chargingPilesByStationId = new ChargeDao(connection).getChargingPilesByStationId(stationId);
        ConnectionPoolManager.closeConnection(connection);
        return chargingPilesByStationId;
    }

    public void setPileState(int pileId, int state) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        ChargeDao chargeDao = new ChargeDao(connection);
        try {
            chargeDao.setPileState(pileId,state);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void deleteChargingPileById(int pileId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        ChargeDao chargeDao = new ChargeDao(connection);
        try {
            chargeDao.deleteChargingPileById(pileId);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }

    public void addChargingPile(int stationId) throws SQLException, InterruptedException {
        connection=ConnectionPoolManager.getConnection();
        TransactionManager transactionManager=new TransactionManager(connection);
        transactionManager.beginTransaction();
        try {
            new ChargeDao(connection).addChargingPile(stationId);
        } catch (SQLException | InterruptedException e) {
            transactionManager.rollback();
            throw new RuntimeException(e);
        }
        transactionManager.commit();
        ConnectionPoolManager.closeConnection(connection);
    }
}
