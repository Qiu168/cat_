package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.ParkingLotEntity;
import com.HuangTaiQi.www.po.ParkingSpotEntity;
import com.HuangTaiQi.www.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ParkDao{
    private Connection connection= DBUtil.getConnection();

    BaseDao baseDao=new BaseDao(connection);
    public List<ParkingLotEntity> getParkLots() throws Exception {
        String sql="select * from parkinglot ";
        return baseDao.selectByParams(sql, ParkingLotEntity.class);
    }

    public void addParkingLot(String location, String name) throws SQLException, InterruptedException {
        String sql="insert into parkinglot(location,name) values(?,?)";
        baseDao.updateCommon(sql,location,name);
    }

    public List<ParkingSpotEntity> getParkSpots(int lotId) throws Exception {
        String sql="select * from parkingspot where location_id=?";
        return baseDao.selectByParams(sql, ParkingSpotEntity.class,lotId);
    }

    public void deleteSpot(int spotId) throws SQLException, InterruptedException {
        String sql="delete from parkingspot where id=?";
        baseDao.updateCommon(sql,spotId);
    }

    public void setSpotState(int spotId, int state) throws SQLException, InterruptedException {
        String sql="update parkingspot set state=? where id=?";
        baseDao.updateCommon(sql,state,spotId);
    }

    public void deleteSpotByLocationId(int lotId) throws SQLException, InterruptedException {
        String sql="delete from parkingspot where location_id=?";
        baseDao.updateCommon(sql,lotId);
    }

    public void deleteLot(int lotId) throws SQLException, InterruptedException {
        String sql="delete from parkinglot where id=?";
        baseDao.updateCommon(sql,lotId);
    }

    public void alterLot(int lotId, String location, String name) throws SQLException, InterruptedException {
        String sql="update parkinglot set location=?,name=? where id=?";
        baseDao.updateCommon(sql,location,name,lotId);
    }

    public void addSpot(int lotId) throws SQLException, InterruptedException {
        String sql="insert into parkingspot(location_id) values(?)";
        baseDao.updateCommon(sql,lotId);
    }

    public void setSpotStateByState(Integer userId, int state) throws SQLException, InterruptedException {
        String sql="update parkingspot set state=? where state=?";
        baseDao.updateCommon(sql,state,userId);
    }
}
