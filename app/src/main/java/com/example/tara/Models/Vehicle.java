package com.example.tara.Models;

public class Vehicle {
    String bmy, plateNumber;

    Vehicle(){

    }
    public Vehicle(String bmy, String plateNumber) {
        this.bmy = bmy;
        this.plateNumber = plateNumber;
    }

    public String getBmy() {
        return bmy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
}
