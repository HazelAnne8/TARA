package com.example.tara.Models;

public class ExploreModel  {
    String carImage, bmy, city, price;

    ExploreModel()
    {
    }

    public ExploreModel(String carImage, String bmy, String city, String price) {
        this.carImage = carImage;
        this.bmy = bmy;
        this.city = city;
        this.price = price;
    }


    public String getCarImage() {
        return carImage;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getBmy() {
        return bmy;
    }

    public void setBmy(String bmy) {
        this.bmy = bmy;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
