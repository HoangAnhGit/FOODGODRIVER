package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;

public class FreePickOrder {
    @SerializedName("orderId")
    private long orderId;

    @SerializedName("shopLogoUrl")
    private String shopLogoUrl;

    @SerializedName("shopName")
    private String shopName;

    @SerializedName("distance")
    private String distance;

    @SerializedName("mainDish")
    private String mainDish;

    @SerializedName("moreItems")
    private String moreItems;

    @SerializedName("destination")
    private String destination;

    @SerializedName("totalPrice")
    private String totalPrice;

    @SerializedName("income")
    private String income;

    public FreePickOrder(long orderId, String shopLogoUrl, String shopName, String distance, String mainDish, String moreItems, String destination, String totalPrice, String income) {
        this.orderId = orderId;
        this.shopLogoUrl = shopLogoUrl;
        this.shopName = shopName;
        this.distance = distance;
        this.mainDish = mainDish;
        this.moreItems = moreItems;
        this.destination = destination;
        this.totalPrice = totalPrice;
        this.income = income;
    }

    // Getters
    public long getOrderId() { return orderId; }
    public String getShopLogoUrl() { return shopLogoUrl; }
    public String getShopName() { return shopName; }
    public String getDistance() { return distance; }
    public String getMainDish() { return mainDish; }
    public String getMoreItems() { return moreItems; }
    public String getDestination() { return destination; }
    public String getTotalPrice() { return totalPrice; }
    public String getIncome() { return income; }
}
