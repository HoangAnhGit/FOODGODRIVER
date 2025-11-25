package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class CurrentDeliveryResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private CurrentDeliveryData data;

    public String getMessage() {
        return message;
    }

    public CurrentDeliveryData getData() {
        return data;
    }
}
