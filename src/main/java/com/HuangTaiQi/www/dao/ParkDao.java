package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ParkDao extends BaseDao{
    private Connection connection;
    public ParkDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public List<ParkingLotEntity> getParkLots() throws Exception {
        String sql="select * from parkinglot ";
        return selectByParams(sql, ParkingLotEntity.class);
    }

    public void addParkingLot(String location, String name) throws SQLException, InterruptedException {
        String sql="insert into parkinglot(location,name) values(?,?)";
        updateCommon(sql,location,name);
    }

    public List<ParkingSpotEntity> getParkSpots(int lotId) throws Exception {
        String sql="select * from parkinglot where location_id=?";
        return selectByParams(sql, ParkingSpotEntity.class,lotId);
    }

    public void deleteSpot(int spotId) throws SQLException, InterruptedException {
        String sql="delete from parkingspot where id=?";
        updateCommon(sql,spotId);
    }

    public void setSpotState(int spotId, int state) throws SQLException, InterruptedException {
        String sql="update parkingspot set state=? where id=?";
        updateCommon(sql,spotId,state);
    }

    public void deleteSpotByLocationId(int lotId) throws SQLException, InterruptedException {
        String sql="delete from parkingspot where location_id=?";
        updateCommon(sql,lotId);
    }

    public void deleteLot(int lotId) throws SQLException, InterruptedException {
        String sql="delete from parkinglot where id=?";
        updateCommon(sql,lotId);
    }

    public void alterLot(int lotId, String location, String name) throws SQLException, InterruptedException {
        String sql="update parkinglot set location=?,name=? where id=?";
        updateCommon(sql,location,name,lotId);
    }
}
