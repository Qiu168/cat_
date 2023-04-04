package com.HuangTaiQi.www.po;

import java.util.List;

public class ChargingPileBean {
    private Integer id;
    private Boolean state;
    private Integer stationId;
    private Integer freeTime;

    private List<Integer> pileSituation;

    public ChargingPileBean(ChargingPileEntity chargingPileEntity) {
        this.id=chargingPileEntity.getId();
        this.state=chargingPileEntity.getState();
        this.stationId=chargingPileEntity.getStationId();
        this.pileSituation.add(chargingPileEntity.getSixSeven());
        this.pileSituation.add(chargingPileEntity.getSevenEight());
        this.pileSituation.add(chargingPileEntity.getEightNine());
        this.pileSituation.add(chargingPileEntity.getNineTen());
        this.pileSituation.add(chargingPileEntity.getTenEleven());
        this.pileSituation.add(chargingPileEntity.getElevenTwelve());
        this.pileSituation.add(chargingPileEntity.getTwelveThirteen());
        this.pileSituation.add(chargingPileEntity.getThirteenFourteen());
        this.pileSituation.add(chargingPileEntity.getFourteenFifteen());
        this.pileSituation.add(chargingPileEntity.getFifteenSixteen());
        this.pileSituation.add(chargingPileEntity.getSixteenSeventeen());
        this.pileSituation.add(chargingPileEntity.getSeventeenEighteen());
        this.pileSituation.add(chargingPileEntity.getEighteenNineteen());
        this.pileSituation.add(chargingPileEntity.getNineteenTwenty());
        this.pileSituation.add(chargingPileEntity.getTwentyTwentyOne());
        this.pileSituation.add(chargingPileEntity.getTOneTwo());
        this.pileSituation.add(chargingPileEntity.getTTwoThree());
        this.pileSituation.add(chargingPileEntity.getTThreeFour());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public List<Integer> getPileSituation() {
        return pileSituation;
    }

    public void setPileSituation(List<Integer> pileSituation) {
        this.pileSituation = pileSituation;
    }


    public String toMyString(int min) {
        return "ChargingPileBean{" +
                "id=" + id +
                ", state=" + state +
                ", stationId=" + stationId +
                "你可以使用：" + (freeTime-((min==0)?0:1)) +
                "小时" + (min) +
                "分钟}";
    }
}
