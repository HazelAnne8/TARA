package com.example.tara.Models;

public class LocationModel {
    public String addressLine1, addressLine2, city, postcode, province;

    public LocationModel(){
    }

    public LocationModel(String addressLine1, String addressLine2, String city, String postcode, String province) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postcode = postcode;
        this.province = province;
    }
}
