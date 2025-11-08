package com.example.foodgocustomer.Repository;

import android.content.Context;
import android.util.Log; // <-- Thêm Log

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.foodgodriver.Network.Api;
import com.example.foodgodriver.Network.ApiClient;
import com.example.foodgodriver.Network.DTO.LoginRequest;
import com.example.foodgodriver.Network.DTO.LoginResponse;
import com.example.foodgodriver.Network.DTO.RegisterRequest;
import com.example.foodgodriver.Network.DTO.RegisterResponse;
import com.example.foodgodriver.Network.TokenManager;
import com.example.foodgodriver.Utils.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {

    private final Api authApi;

    public AuthRepository() {
        authApi = ApiClient.getClient().create(Api.class);
    }

    public LiveData<Result<LoginResponse>> login(Context context, String phone, String password) {
        MutableLiveData<Result<LoginResponse>> resultLiveData = new MutableLiveData<>();
        LoginRequest request = new LoginRequest(phone, password);

        authApi.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse data = response.body();
                    TokenManager.getInstance(context).saveToken(data.getToken(), data.getUserType());
                    resultLiveData.setValue(Result.success(response.body()));
                } else {
                    resultLiveData.setValue(Result.error("Sai số điện thoại hoặc mật khẩu"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                resultLiveData.setValue(Result.error("Lỗi kết nối: " + t.getMessage()));
            }
        });

        return resultLiveData;
    }



    public LiveData<Result<RegisterResponse>> register(
            String phone,
            String password,
            String confirm,
            String fullName,
            String licensePlate
    ) {
        MutableLiveData<Result<RegisterResponse>> resultRegister = new MutableLiveData<>();
        resultRegister.setValue(Result.loading());


        RegisterRequest request = new RegisterRequest(phone, password, confirm, fullName, licensePlate);

        authApi.registerCustomer(request).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<RegisterResponse> call, @NonNull Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    resultRegister.setValue(Result.success(response.body()));
                } else {
                    // Log lỗi chi tiết hơn
                    String errorMsg = "Đăng ký thất bại (Mã: " + response.code() + ")";
                    Log.e("AuthRepository", "Lỗi đăng ký: " + response.code() + " - " + response.message());
                    resultRegister.setValue(Result.error(errorMsg));
                }
            }

            @Override
            public void onFailure(@NonNull Call<RegisterResponse> call, @NonNull Throwable t) {
                Log.e("AuthRepository", "Lỗi kết nối đăng ký", t);
                resultRegister.setValue(Result.error("Lỗi kết nối: " + t.getMessage()));
            }
        });

        return resultRegister;
    }
}