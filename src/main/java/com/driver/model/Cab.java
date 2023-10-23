
package com.driver.model;

import javax.persistence.*;


@Entity
@Table
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private Integer perKmRate;

    private boolean Available;

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

    public Cab(Integer Id, Integer perKmRate, boolean available) {
        this.Id = Id;
        this.perKmRate = perKmRate;
        this.Available = available;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public boolean getAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        this.Available = available;
    }
}