package com.example.tara.Models;

public class AboutModel {
    public String year,brand,transmission,drivetrain,seats,type,fuelType,mileage, model, plateNumber;

    public AboutModel(){ }

    public AboutModel(String year, String brand, String transmission, String drivetrain, String seats, String type, String fuelType, String mileage, String model, String plateNumber) {
        this.year = year;
        this.brand = brand;
        this.transmission = transmission;
        this.drivetrain = drivetrain;
        this.seats = seats;
        this.type = type;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.model = model;
        this.plateNumber = plateNumber;
    }
}
