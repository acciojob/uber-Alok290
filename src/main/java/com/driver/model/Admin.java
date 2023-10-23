package com.driver.model;

import javax.persistence.*;


@Entity
@Table
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer AdminId;

    private String Username;

    private String password;


    public Admin(){

    }

    public Admin(Integer AdminId, String Username, String password) {
        this.AdminId = AdminId;
        this.Username = Username;
        this.password = password;
    }

    public Integer getAdminId() {
        return AdminId;
    }

    public void setAdminId(Integer adminId) {
        this.AdminId = adminId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}