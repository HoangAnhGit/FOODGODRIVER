package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

// Đây là DTO khớp với JSON schema mới
public class RegisterRequest {

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("password")
    private String password;

    @SerializedName("confirmPassword")
    private String confirmPassword;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("licensePlate")
    private String licensePlate;

    // Constructor
    public RegisterRequest(String phoneNumber, String password, String confirmPassword, String fullName, String licensePlate) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        this.licensePlate = licensePlate;
    }

    // Getters (không bắt buộc cho request, nhưng nên có)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}