package com.example.tara.Models;

public class Vehicle {
    public String bmy, plateNumber, carUrl;

    Vehicle(){ }

    public Vehicle(String bmy, String plateNumber, String carUrl) {
        this.bmy = bmy;
        this.plateNumber = plateNumber;
        this.carUrl = carUrl;
    }

    public String getBmy() {
        return bmy;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getCarUrl() { return carUrl; }
}
