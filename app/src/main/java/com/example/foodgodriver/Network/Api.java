package com.example.foodgodriver.Network;

import com.example.foodgodriver.Network.DTO.ApiResponse;
import com.example.foodgodriver.Network.DTO.AvailabilityResponse;
import com.example.foodgodriver.Network.DTO.LoginRequest;
import com.example.foodgodriver.Network.DTO.LoginResponse;
import com.example.foodgodriver.Network.DTO.RegisterRequest;
import com.example.foodgodriver.Network.DTO.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Api {

    @POST("/api/Auth/register/shipper")
    Call<RegisterResponse> registerCustomer(@Body RegisterRequest request);


    @POST("/api/Auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @Headers("Content-Type: application/json")
    @PUT("/api/Shipper/availability") // <-- Đổi đường dẫn nếu API của bạn khác
    Call<AvailabilityResponse> updateAvailability(@Body boolean isAvailable);

}
