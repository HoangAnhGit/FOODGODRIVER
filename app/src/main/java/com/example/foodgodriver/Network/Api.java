package com.example.foodgodriver.Network;

import com.example.foodgodriver.Network.DTO.ApiResponse;
import com.example.foodgodriver.Network.DTO.AvailabilityResponse;
import com.example.foodgodriver.Network.DTO.CurrentDeliveryResponse;
import com.example.foodgodriver.Network.DTO.FreePickResponse;
import com.example.foodgodriver.Network.DTO.LoginRequest;
import com.example.foodgodriver.Network.DTO.LoginResponse;
import com.example.foodgodriver.Network.DTO.OrderItemsResponse;
import com.example.foodgodriver.Network.DTO.RegisterRequest;
import com.example.foodgodriver.Network.DTO.RegisterResponse;
import com.example.foodgodriver.Network.DTO.ShipperNameResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    @POST("/api/Auth/register/shipper")
    Call<RegisterResponse> registerCustomer(@Body RegisterRequest request);


    @POST("/api/Auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @Headers("Content-Type: application/json")
    @PUT("/api/Shipper/availability") // <-- Đổi đường dẫn nếu API của bạn khác
    Call<AvailabilityResponse> updateAvailability(@Body boolean isAvailable);

    @GET("/api/Shipper/orders/free-pick")
    Call<FreePickResponse> getFreePickOrders();

    @POST("/api/Shipper/orders/{orderId}/pick")
    Call<Object> pickOrder(@Path("orderId") long orderId);

    @GET("/api/Shipper/current-delivery")
    Call<CurrentDeliveryResponse> getCurrentDelivery();

    @GET("/api/Shipper/orders/{orderId}/items")
    Call<OrderItemsResponse> getOrderItems(@Path("orderId") long orderId);

    @POST("/api/Shipper/orders/{orderId}/complete")
    Call<Object> completeOrder(@Path("orderId") long orderId);

    @GET("/api/Shipper/profile/name")
    Call<ShipperNameResponse> getShipperName();

}
