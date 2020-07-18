package com.example.newpark;

public class UserHelperClass {
    String name,email,password,vehicleid;

    public UserHelperClass() {
    }

    public UserHelperClass(String name, String email, String password, String vehicleid) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.vehicleid = vehicleid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }
}
