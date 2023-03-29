package com.HuangTaiQi.www.po;

public class ParkingLotEntity {
    private Integer id;
    private String location;
    private String name;

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

    @Override
    public String toString() {
        return "ParkingLotEntity{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
