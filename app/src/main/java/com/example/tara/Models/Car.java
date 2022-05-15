package com.example.tara.Models;



public class Car  {
    String carUrl, bmy, location, priceRate, userId;

    Car()
    {
    }



    public Car(String carUrl, String bmy, String location, String priceRate, String userId){
        this.carUrl = carUrl;
        this.bmy = bmy;
        this.location = location;
        this.priceRate = priceRate;
        this.userId = userId;
    }


    public String getCarUrl() {
        return carUrl;
    }

    public String getBmy() {
        return bmy;
    }

    public String getLocation() {
        return location;
    }

    public String getPriceRate() {
        return priceRate;
    }

    public String getUserId() {
        return userId;
    }
}
