package com.example.tara.Models;

import android.widget.TextView;

public class CarHost {
    public String address1,address2,city,postcode,province,year,brand,transmission,drivetrain,seats,type,fuelType,mileage,model,plateNumber
            ,insuranceUrl,priceRate,description,carUrl,bmy,location;

    public CarHost(){

    }
    public CarHost(String address1, String address2, String city, String postcode, String province,
                   String year, String brand, String transmission, String drivetrain, String seats,
                   String type, String fuelType, String mileage, String model, String plateNumber,
                   String priceRate, String description, String carUrl, String bmy, String location) {
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.postcode = postcode;
        this.province = province;
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
        this.insuranceUrl = insuranceUrl;
        this.priceRate = priceRate;
        this.description = description;
        this.carUrl = carUrl;
        this.bmy = bmy;
        this.location = location;
    }
}

