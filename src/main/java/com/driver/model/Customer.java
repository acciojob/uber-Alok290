
package com.driver.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer CustomerId;


    private String Mobile;

    private String password;


    @OneToMany (mappedBy = "customer",cascade = CascadeType.ALL)
    private List<TripBooking> tripBookingList = new ArrayList<>();


    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public Customer(){

    }

    public Customer(Integer CustomerId, String Mobile, String password) {
        this.CustomerId = CustomerId;
        this.Mobile = Mobile;
        this.password = password;
    }

    public Integer getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Integer customerId) {
        this.CustomerId = customerId;
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
}