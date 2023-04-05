package com.HuangTaiQi.www.dao.impl;

import com.HuangTaiQi.www.dao.ChargeDao;
import com.HuangTaiQi.www.po.ChargingPileBean;
import com.HuangTaiQi.www.po.ChargingPileEntity;
import com.HuangTaiQi.www.po.ChargingStationEntity;
import com.HuangTaiQi.www.utils.DBUtil;
import com.HuangTaiQi.www.utils.sql.SQLBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ChargeDaoImpl implements ChargeDao {
    private static final Connection connection= DBUtil.getConnection();

    static BaseDao baseDao=new BaseDao(connection);

    private static ChargeDaoImpl instance;
    private ChargeDaoImpl (){}
    public static synchronized ChargeDaoImpl getInstance() {
        if (instance == null) {
            instance = new ChargeDaoImpl();
        }
        return instance;
    }

    public List<ChargingStationEntity> getChargingStations() throws Exception {
        String sql=new SQLBuilder("chargingstation").select("*").buildSelect();
        return baseDao.selectByParams(sql, ChargingStationEntity.class);
    }

    public void addChargingStation(String location, String name, int open, int close) throws SQLException, InterruptedException {
        String sql=new SQLBuilder("chargingstation")
                .insert("location")
                .insert("name")
                .insert("open")
                .insert("close")
                .buildInsert();
        baseDao.updateCommon(sql,location,name,open,close);

    }

    public void updateChargingStation(int stationId, String location, String name, int open, int close) throws SQLException, InterruptedException {
        String sql="update chargingstation set location=?,name=?,open=?,close=? where id=?";
        baseDao.updateCommon(sql,location,name,open,close,stationId);
    }

    public void deleteChargingStationById(int stationId) throws SQLException, InterruptedException {
        String sql="delete from chargingstation where id=?";
        baseDao.updateCommon(sql,stationId);
    }

    public List<ChargingPileEntity> getChargingPilesByStationId(int stationId) throws Exception {
        String sql=new SQLBuilder("chargingpile").select("*").where("station_id").buildSelect();
        return baseDao.selectByParams(sql, ChargingPileEntity.class,stationId);
    }

    public void deleteChargingPileByStationId(int stationId) throws SQLException, InterruptedException {
        String sql="delete from chargingpile where station_id=?";
        baseDao.updateCommon(sql,stationId);
    }

    public void setPileState(int pileId, int state) throws SQLException, InterruptedException {
        String sql="update chargingpile set state=? where id=?";
        baseDao.updateCommon(sql,state,pileId);
    }

    public void deleteChargingPileById(int pileId) throws SQLException, InterruptedException {
        String sql="delete from chargingpile where id=?";
        baseDao.updateCommon(sql,pileId);
    }

    public void addChargingPile(int stationId) throws SQLException, InterruptedException {
        String sql="insert into chargingpile(station_id) values(?)";
        baseDao.updateCommon(sql,stationId);
    }

    public void setPileTime(ChargingPileBean pile) throws SQLException, InterruptedException {
        String sql="update chargingpile set six_seven=?,seven_eight=?,eight_nine=?,nine_ten=?,ten_eleven=?," +
                "eleven_twelve=?,twelve_thirteen=?,thirteen_fourteen=?,fourteen_fifteen=?,fifteen_sixteen=?," +
                "sixteen_seventeen=?,seventeen_eighteen=?,eighteen_nineteen=?,nineteen_twenty=?,twenty_twenty_one=?" +
                ",t_one_two=?,t_two_three=?,t_three_four=?  where id=?";
        List<Integer> pileSituation = pile.getPileSituation();
        baseDao.updateCommon(sql,
                pileSituation.get(0),
                pileSituation.get(1),
                pileSituation.get(2),
                pileSituation.get(3),
                pileSituation.get(4),
                pileSituation.get(5),
                pileSituation.get(6),
                pileSituation.get(7),
                pileSituation.get(8),
                pileSituation.get(9),
                pileSituation.get(10),
                pileSituation.get(11),
                pileSituation.get(12),
                pileSituation.get(13),
                pileSituation.get(14),
                pileSituation.get(15),
                pileSituation.get(16),
                pileSituation.get(17),
                pile.getId());
    }

    public ChargingPileEntity getPileById(int pileId) throws Exception {
        String sql=new SQLBuilder("chargingpile").select("*").where("id").buildSelect();
        List<ChargingPileEntity> list = baseDao.selectByParams(sql, ChargingPileEntity.class, pileId);
        return list == null ? null : list.get(0);
    }
    public static void refresh() throws SQLException {
        String sql="update chargingpile set six_seven=?,seven_eight=?,eight_nine=?,nine_ten=?,ten_eleven=?," +
                "eleven_twelve=?,twelve_thirteen=?,thirteen_fourteen=?,fourteen_fifteen=?,fifteen_sixteen=?," +
                "sixteen_seventeen=?,seventeen_eighteen=?,eighteen_nineteen=?,nineteen_twenty=?,twenty_twenty_one=?" +
                ",t_one_two=?,t_two_three=?,t_three_four=? ";
        baseDao.updateCommon(sql, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

}
