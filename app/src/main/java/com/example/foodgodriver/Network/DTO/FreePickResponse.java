package com.example.foodgodriver.Network.DTO;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class FreePickResponse {
    @SerializedName("message")
    private String message;

    @SerializedName("pageNumber")
    private int pageNumber;

    @SerializedName("pageSize")
    private int pageSize;

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("totalRecords")
    private int totalRecords;

    @SerializedName("maxDistanceKm")
    private int maxDistanceKm;

    @SerializedName("shipperLocation")
    private ShipperLocation shipperLocation;

    @SerializedName("data")
    private List<FreePickOrder> data;

    // Getters
    public String getMessage() { return message; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public int getTotalPages() { return totalPages; }
    public int getTotalRecords() { return totalRecords; }
    public int getMaxDistanceKm() { return maxDistanceKm; }
    public ShipperLocation getShipperLocation() { return shipperLocation; }
    public List<FreePickOrder> getData() { return data; }
}
