package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class OrderItem {
    @SerializedName("dishId")
    private int dishId;
    @SerializedName("dishName")
    private String dishName;
    @SerializedName("quantity")
    private int quantity;
    @SerializedName("unitPrice")
    private double unitPrice;
    @SerializedName("lineTotal")
    private double lineTotal;

    // Getters
    public int getDishId() { return dishId; }
    public String getDishName() { return dishName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getLineTotal() { return lineTotal; }
}
