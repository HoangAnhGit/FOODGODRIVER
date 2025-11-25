package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class ShipperNameResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("fullName")
    private String fullName;

    public String getMessage() {
        return message;
    }

    public String getFullName() {
        return fullName;
    }
}
