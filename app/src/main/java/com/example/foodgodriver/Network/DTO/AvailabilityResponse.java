package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class AvailabilityResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("isAvailable")
    private boolean isAvailable;

    // Getters
    public String getMessage() {
        return message;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
