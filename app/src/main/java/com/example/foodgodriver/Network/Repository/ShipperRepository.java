package com.example.foodgodriver.Network.Repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodgodriver.Network.Api;
import com.example.foodgodriver.Network.ApiClient;
import com.example.foodgodriver.Network.DTO.AvailabilityResponse;
import com.example.foodgodriver.Network.DTO.CurrentDeliveryResponse;
import com.example.foodgodriver.Network.DTO.FreePickResponse;
import com.example.foodgodriver.Network.DTO.OrderItemsResponse;
import com.example.foodgodriver.Network.DTO.ShipperNameResponse;
import com.example.foodgodriver.Utils.Result;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipperRepository {
    private Api shipperApi;
    private Context context;

    public ShipperRepository(Context context) {
        this.context = context.getApplicationContext();
        this.shipperApi = ApiClient.getClientAuth(context).create(Api.class);
    }

    private String parseErrorMessage(Response<?> response) {
        String errorMessage = "An unknown error occurred (Code: " + response.code() + ")";
        if (response.errorBody() != null) {
            try {
                errorMessage = response.errorBody().string();
            } catch (IOException e) {
                Log.e("ShipperRepository", "Error parsing error body", e);
            }
        }
        return errorMessage;
    }

    public LiveData<Result<AvailabilityResponse>> updateAvailability(boolean isAvailable) {
        MutableLiveData<Result<AvailabilityResponse>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.updateAvailability(isAvailable).enqueue(new Callback<AvailabilityResponse>() {
            @Override
            public void onResponse(@NonNull Call<AvailabilityResponse> call, @NonNull Response<AvailabilityResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "updateAvailability failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AvailabilityResponse> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on updateAvailability", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<FreePickResponse>> getFreePickOrders() {
        MutableLiveData<Result<FreePickResponse>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.getFreePickOrders().enqueue(new Callback<FreePickResponse>() {
            @Override
            public void onResponse(@NonNull Call<FreePickResponse> call, @NonNull Response<FreePickResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "getFreePickOrders failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<FreePickResponse> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on getFreePickOrders", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<Object>> pickOrder(long orderId) {
        MutableLiveData<Result<Object>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.pickOrder(orderId).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                if (response.isSuccessful()) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "pickOrder failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on pickOrder", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<CurrentDeliveryResponse>> getCurrentDelivery() {
        MutableLiveData<Result<CurrentDeliveryResponse>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.getCurrentDelivery().enqueue(new Callback<CurrentDeliveryResponse>() {
            @Override
            public void onResponse(@NonNull Call<CurrentDeliveryResponse> call, @NonNull Response<CurrentDeliveryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "getCurrentDelivery failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<CurrentDeliveryResponse> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on getCurrentDelivery", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<OrderItemsResponse>> getOrderItems(long orderId) {
        MutableLiveData<Result<OrderItemsResponse>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.getOrderItems(orderId).enqueue(new Callback<OrderItemsResponse>() {
            @Override
            public void onResponse(@NonNull Call<OrderItemsResponse> call, @NonNull Response<OrderItemsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "getOrderItems failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderItemsResponse> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on getOrderItems", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<Object>> completeOrder(long orderId) {
        MutableLiveData<Result<Object>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.completeOrder(orderId).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                if (response.isSuccessful()) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "completeOrder failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on completeOrder", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }

    public LiveData<Result<ShipperNameResponse>> getShipperName() {
        MutableLiveData<Result<ShipperNameResponse>> result = new MutableLiveData<>();
        result.setValue(Result.loading());

        shipperApi.getShipperName().enqueue(new Callback<ShipperNameResponse>() {
            @Override
            public void onResponse(@NonNull Call<ShipperNameResponse> call, @NonNull Response<ShipperNameResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(Result.success(response.body()));
                } else {
                    String error = parseErrorMessage(response);
                    Log.e("ShipperRepository", "getShipperName failed: " + error);
                    result.setValue(Result.error(error));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ShipperNameResponse> call, @NonNull Throwable t) {
                Log.e("ShipperRepository", "Network error on getShipperName", t);
                result.setValue(Result.error("Network error: " + t.getMessage()));
            }
        });

        return result;
    }
}
