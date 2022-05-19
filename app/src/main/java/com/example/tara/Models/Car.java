package com.example.tara.Models;

public class Car  {
    public String exterior1Url, bmy, location, priceRate, userId;

    Car(){ }

    public Car(String exterior1Url, String bmy, String location, String priceRate, String userId){
        this.exterior1Url = exterior1Url;
        this.bmy = bmy;
        this.location = location;
        this.priceRate = priceRate;
        this.userId = userId;
    }


    public String getCarUrl() {
        return exterior1Url;
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
