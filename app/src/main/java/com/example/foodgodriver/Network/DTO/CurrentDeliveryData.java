package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class CurrentDeliveryData {

    @SerializedName("orderId")
    private long orderId;
    @SerializedName("orderCode")
    private String orderCode;
    @SerializedName("shipperIncome")
    private double shipperIncome;
    @SerializedName("surchargeFee")
    private double surchargeFee;
    @SerializedName("surchargeReason")
    private String surchargeReason;
    @SerializedName("totalDistanceKm")
    private double totalDistanceKm;
    @SerializedName("estimatedPickupTime")
    private String estimatedPickupTime;
    @SerializedName("estimatedDeliveryTime")
    private String estimatedDeliveryTime;
    @SerializedName("restaurantName")
    private String restaurantName;
    @SerializedName("restaurantAddress")
    private String restaurantAddress;
    @SerializedName("restaurantLat")
    private double restaurantLat;
    @SerializedName("restaurantLng")
    private double restaurantLng;
    @SerializedName("customerName")
    private String customerName;
    @SerializedName("deliveryAddress")
    private String deliveryAddress;
    @SerializedName("deliveryLat")
    private double deliveryLat;
    @SerializedName("deliveryLng")
    private double deliveryLng;
    @SerializedName("paymentMethod")
    private String paymentMethod;
    @SerializedName("amountToCollect")
    private double amountToCollect;

    // Getters
    public long getOrderId() { return orderId; }
    public String getOrderCode() { return orderCode; }
    public double getShipperIncome() { return shipperIncome; }
    public double getSurchargeFee() { return surchargeFee; }
    public String getSurchargeReason() { return surchargeReason; }
    public double getTotalDistanceKm() { return totalDistanceKm; }
    public String getEstimatedPickupTime() { return estimatedPickupTime; }
    public String getEstimatedDeliveryTime() { return estimatedDeliveryTime; }
    public String getRestaurantName() { return restaurantName; }
    public String getRestaurantAddress() { return restaurantAddress; }
    public double getRestaurantLat() { return restaurantLat; }
    public double getRestaurantLng() { return restaurantLng; }
    public String getCustomerName() { return customerName; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public double getDeliveryLat() { return deliveryLat; }
    public double getDeliveryLng() { return deliveryLng; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountToCollect() { return amountToCollect; }
}
