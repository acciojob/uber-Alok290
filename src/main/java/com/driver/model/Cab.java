
package com.driver.model;

import io.swagger.models.auth.In;

import javax.persistence.*;


@Entity
@Table
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cabId;

    private Integer perKmRate;

    private boolean available;

    @OneToOne
    @JoinColumn
    private Driver driver;


    public Cab(){

    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Cab(Integer cabId, Integer perKmRate, boolean available) {
        this.cabId = cabId;
        this.perKmRate = perKmRate;
        this.available = available;
    }

    public Integer getCabId() {
        return cabId;
    }

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean getisAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}