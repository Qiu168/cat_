package com.HuangTaiQi.www.dao;

import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.utils.SQLBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChargeDao extends BaseDao{
    private Connection connection;

    public ChargeDao(Connection connection) {
        super(connection);
        this.connection = connection;
    }

    public List<ChargingStationEntity> getChargingStations() throws Exception {
        String sql=new SQLBuilder("chargingstation").select("*").buildSelect();
        return selectByParams(sql, ChargingStationEntity.class);
    }

    public void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException {
        String sql=new SQLBuilder("chargingstation")
                .insert("location")
                .insert("name")
                .insert("open")
                .insert("close")
                .buildInsert();
        updateCommon(sql,location,name,open,close);

    }

    public void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException {
        String sql="update chargingstation set location=?,name=?,open=?,close=? where id=?";
        updateCommon(sql,location,name,open,close,stationId);
    }

    public void deleteChargingStationById(int stationId) throws SQLException, InterruptedException {
        String sql="delete from chargingstation where id=?";
        updateCommon(sql,stationId);
    }

    public List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception {
        String sql=new SQLBuilder("chargingstation").select("*").where("station_id").buildSelect();
        return selectByParams(sql, ChargingPileEntity.class,stationId);
    }

    public void deleteChargingPileByStationId(int stationId) throws SQLException, InterruptedException {
        String sql="delete from chargingpile where station_id=?";
        updateCommon(sql,stationId);
    }

    public void setPileState(int pileId, int state) throws SQLException, InterruptedException {
        String sql="update chargingpile set state=? where id=?";
        updateCommon(sql,state,pileId);
    }

    public void deleteChargingPileById(int pileId) throws SQLException, InterruptedException {
        String sql="delete from chargingpile where id=?";
        updateCommon(sql,pileId);
    }

    public void addChargingPile(int stationId) throws SQLException, InterruptedException {
        String sql="insert into chargingpile(station_id) values(?)";
        updateCommon(sql,stationId);
    }
}
