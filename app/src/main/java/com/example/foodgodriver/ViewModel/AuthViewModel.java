package com.example.foodgodriver.ViewModel; // (Hoặc package ViewModel của bạn)

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodgodriver.Network.DTO.LoginResponse;
import com.example.foodgodriver.Network.DTO.RegisterResponse;
import com.example.foodgodriver.Utils.Result;
import com.example.foodgocustomer.Repository.AuthRepository;

public class AuthViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository();
    }


    public LiveData<Result<LoginResponse>> login(String phone, String password) {

        Context appContext = getApplication().getApplicationContext();
        return authRepository.login(appContext, phone, password);
    }

    public LiveData<Result<RegisterResponse>> register(
            String phone,
            String password,
            String confirm,
            String fullName,
            String licensePlate
    ) {

        return authRepository.register(phone, password, confirm, fullName, licensePlate);
    }
}