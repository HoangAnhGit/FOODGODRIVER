package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class OrderItemsResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("orderId")
    private long orderId;
    @SerializedName("orderCode")
    private String orderCode;
    @SerializedName("restaurantName")
    private String restaurantName;
    @SerializedName("orderStatus")
    private String orderStatus;
    @SerializedName("totalItems")
    private int totalItems;
    @SerializedName("subtotal")
    private double subtotal;
    @SerializedName("shippingFee")
    private double shippingFee;
    @SerializedName("totalAmount")
    private double totalAmount;
    @SerializedName("items")
    private List<OrderItem> items;

    // Getters
    public String getMessage() { return message; }
    public long getOrderId() { return orderId; }
    public String getOrderCode() { return orderCode; }
    public String getRestaurantName() { return restaurantName; }
    public String getOrderStatus() { return orderStatus; }
    public int getTotalItems() { return totalItems; }
    public double getSubtotal() { return subtotal; }
    public double getShippingFee() { return shippingFee; }
    public double getTotalAmount() { return totalAmount; }
    public List<OrderItem> getItems() { return items; }
}
