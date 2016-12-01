package com.example.pikachu.test;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Pikachu on 11/26/16.
 */

public class Stores {
    String name;
    String address;
    String city;
    String region;
    String country;
    String hours;
    public Stores(String name, String address,String city, String region, String country, String hours){

        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
        this.hours = hours;
    }
}
