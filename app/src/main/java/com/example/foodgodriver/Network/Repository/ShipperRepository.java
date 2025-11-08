package com.example.foodgodriver.Network.Repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodgodriver.Network.Api;
import com.example.foodgodriver.Network.ApiClient;
import com.example.foodgodriver.Network.DTO.AvailabilityResponse;
import com.example.foodgodriver.Utils.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShipperRepository {
    private Api shipperApi;
    private Context context;

    public ShipperRepository(Context context) {
        this.context = context.getApplicationContext();
        // API này yêu cầu xác thực (token)
        this.shipperApi = ApiClient.getClientAuth(context).create(Api.class);
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
                    // Lỗi 401 (Token hết hạn), 404 (Không tìm thấy Shipper), 500 (Lỗi server)
                    Log.e("ShipperRepository", "Cập nhật trạng thái thất bại: " + response.code());
                    result.setValue(Result.error("Cập nhật thất bại (Mã: " + response.code() + ")"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<AvailabilityResponse> call, @NonNull Throwable t) {
                // Lỗi kết nối
                Log.e("ShipperRepository", "Lỗi mạng khi cập nhật trạng thái", t);
                result.setValue(Result.error("Lỗi kết nối: " + t.getMessage()));
            }
        });

        return result;
    }
}
