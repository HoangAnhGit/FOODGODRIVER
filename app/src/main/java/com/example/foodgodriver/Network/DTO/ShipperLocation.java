package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class ShipperLocation {
    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    // Getters
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
