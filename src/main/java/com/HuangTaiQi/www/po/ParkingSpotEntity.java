package com.HuangTaiQi.www.po;

public class ParkingSpotEntity {
    private Integer id;
    private Integer state;
    private Integer locationId;
    public static int FREE=0;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "ParkingSpotEntity{" +
                "id=" + id +
                ", state=" + state +
                ", locationId=" + locationId +
                '}';
    }
}
