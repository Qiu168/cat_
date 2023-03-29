package com.HuangTaiQi.www.po;

public class ChargingStationEntity {
    private Integer id;
    private String location;
    private String name;
    private Integer open;
    private Integer close;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getClose() {
        return close;
    }

    public void setClose(Integer close) {
        this.close = close;
    }


    @Override
    public String toString() {
        return "ChargingStationEntity{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", open=" + open +
                ", close=" + close +
                '}';
    }
}
