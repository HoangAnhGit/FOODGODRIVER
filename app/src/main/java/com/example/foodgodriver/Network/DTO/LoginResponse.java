package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("token")
    private String token;
    @SerializedName("userType")
    private String userType;
    @SerializedName("username") // Thêm trường username
    private String username;

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getUserType() {
        return userType;
    }

    public String getUsername() { // Thêm getter cho username
        return username;
    }
}
