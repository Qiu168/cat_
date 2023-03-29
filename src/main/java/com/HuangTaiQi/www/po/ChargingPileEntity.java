package com.HuangTaiQi.www.po;

public class ChargingPileEntity {
    private Integer id;
    private Boolean state;
    private Integer stationId;

    /**
     * 下面是时间段字段，
     * -1代表禁用
     *  0代表空闲
     *  正整数代表使用/预约者的学号
     */
    private Integer sixSeven;
    private Integer sevenEight;
    private Integer eightNine;
    private Integer nineTen;
    private Integer tenEleven;
    private Integer elevenTwelve;
    private Integer twelveThirteen;
    private Integer thirteenFourteen;
    private Integer fourteenFifteen;
    private Integer fifteenSixteen;
    private Integer sixteenSeventeen;
    private Integer seventeenEighteen;
    private Integer eighteenNineteen;
    private Integer nineteenTwenty;
    private Integer twentyTwentyOne;
    private Integer tOneTwo;
    private Integer tTwoThree;
    private Integer tThreeFour;

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

    public Integer getSixSeven() {
        return sixSeven;
    }

    public void setSixSeven(Integer sixSeven) {
        this.sixSeven = sixSeven;
    }

    public Integer getSevenEight() {
        return sevenEight;
    }

    public void setSevenEight(Integer sevenEight) {
        this.sevenEight = sevenEight;
    }

    public Integer getEightNine() {
        return eightNine;
    }

    public void setEightNine(Integer eightNine) {
        this.eightNine = eightNine;
    }

    public Integer getNineTen() {
        return nineTen;
    }

    public void setNineTen(Integer nineTen) {
        this.nineTen = nineTen;
    }

    public Integer getTenEleven() {
        return tenEleven;
    }

    public void setTenEleven(Integer tenEleven) {
        this.tenEleven = tenEleven;
    }

    public Integer getElevenTwelve() {
        return elevenTwelve;
    }

    public void setElevenTwelve(Integer elevenTwelve) {
        this.elevenTwelve = elevenTwelve;
    }

    public Integer getTwelveThirteen() {
        return twelveThirteen;
    }

    public void setTwelveThirteen(Integer twelveThirteen) {
        this.twelveThirteen = twelveThirteen;
    }

    public Integer getThirteenFourteen() {
        return thirteenFourteen;
    }

    public void setThirteenFourteen(Integer thirteenFourteen) {
        this.thirteenFourteen = thirteenFourteen;
    }

    public Integer getFourteenFifteen() {
        return fourteenFifteen;
    }

    public void setFourteenFifteen(Integer fourteenFifteen) {
        this.fourteenFifteen = fourteenFifteen;
    }

    public Integer getFifteenSixteen() {
        return fifteenSixteen;
    }

    public void setFifteenSixteen(Integer fifteenSixteen) {
        this.fifteenSixteen = fifteenSixteen;
    }

    public Integer getSixteenSeventeen() {
        return sixteenSeventeen;
    }

    public void setSixteenSeventeen(Integer sixTeenSeventeen) {
        this.sixteenSeventeen = sixTeenSeventeen;
    }

    public Integer getSeventeenEighteen() {
        return seventeenEighteen;
    }

    public void setSeventeenEighteen(Integer seventeenEighteen) {
        this.seventeenEighteen = seventeenEighteen;
    }

    public Integer getEighteenNineteen() {
        return eighteenNineteen;
    }

    public void setEighteenNineteen(Integer eighteenNineteen) {
        this.eighteenNineteen = eighteenNineteen;
    }

    public Integer getNineteenTwenty() {
        return nineteenTwenty;
    }

    public void setNineteenTwenty(Integer nineteenTwenty) {
        this.nineteenTwenty = nineteenTwenty;
    }

    public Integer getTwentyTwentyOne() {
        return twentyTwentyOne;
    }

    public void setTwentyTwentyOne(Integer twentyTwentyOne) {
        this.twentyTwentyOne = twentyTwentyOne;
    }

    public Integer getTOneTwo() {
        return tOneTwo;
    }

    public void setTOneTwo(Integer tOneTwo) {
        this.tOneTwo = tOneTwo;
    }

    public Integer getTTwoThree() {
        return tTwoThree;
    }

    public void setTTwoThree(Integer tTwoThree) {
        this.tTwoThree = tTwoThree;
    }

    public Integer getTThreeFour() {
        return tThreeFour;
    }

    public void setTThreeFour(Integer tThreeFour) {
        this.tThreeFour = tThreeFour;
    }

    @Override
    public String toString() {
        return "ChargingPileEntity{" +
                "id=" + id +
                ", state=" + state +
                ", stationId=" + stationId +
                ", 今日目前此充电桩的使用情况如下\n"+
                ", 六点到七点=" + toStringTime(sixSeven) +"\n"+
                ", 七点到八点=" + toStringTime(sevenEight) +"\n"+
                ", 八点到九点=" + toStringTime(eightNine) +"\n"+
                ", 九点到十点=" + toStringTime(nineTen) +"\n"+
                ", 十点到十一点=" + toStringTime(tenEleven) +"\n"+
                ", 十一点到十二点=" + toStringTime(elevenTwelve) +"\n"+
                ", 十二点到十三点=" + toStringTime(twelveThirteen) +"\n"+
                ", 十三点到十四点=" + toStringTime(thirteenFourteen) +"\n"+
                ", 十四点到十五点=" + toStringTime(fourteenFifteen) +"\n"+
                ", 十五点到十六点=" + toStringTime(fifteenSixteen) +"\n"+
                ", 十六点到十七点=" + toStringTime(sixteenSeventeen) +"\n"+
                ", 十七点到十八点=" + toStringTime(seventeenEighteen) +"\n"+
                ", 十八点到十九点=" + toStringTime(eighteenNineteen) +"\n"+
                ", 十九点到二十点=" + toStringTime(nineteenTwenty) +"\n"+
                ", 二十点到二十一点=" + toStringTime(twentyTwentyOne) +"\n"+
                ", 二十一点到二十二点=" + toStringTime(tOneTwo) +"\n"+
                ", 二十二点到二十三点=" + toStringTime(tTwoThree) +"\n"+
                ", 二十三到二十四点=" + toStringTime(tTwoThree) +"\n"+
                "}\n\n";
    }
    private String toStringTime(Integer period){
        if(period==0){
            return "此时间段暂时没有人用";
        }else if(period==-1){
            return "此时间段已被禁用";
        }else{
            return "id为"+period+"的用户正在使用中";
        }
    }
}
