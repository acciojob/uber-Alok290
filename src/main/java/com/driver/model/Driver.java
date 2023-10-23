package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Driver {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer DriverId;

    private String Mobile;


    private String password;

    @OneToOne (mappedBy = "driver",cascade = CascadeType.ALL)
    private Cab cab;

    @OneToMany (mappedBy = "driver",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList = new ArrayList<>();

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public Driver() {
    }

    public Integer getDriverId() {
        return DriverId;
    }

    public void setDriverId(Integer driverId) {
        this.DriverId = driverId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        this.Mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Driver(Integer DriverId, String mobile, String password) {
        this.DriverId = DriverId;
        this.Mobile = mobile;
        this.password = password;
    }
}