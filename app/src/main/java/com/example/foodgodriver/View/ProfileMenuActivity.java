package com.example.foodgodriver.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodgodriver.Network.TokenManager;
import com.example.foodgodriver.ViewModel.ShipperViewModel;
import com.example.foodgodriver.databinding.ActivityProfileMenuBinding;

public class ProfileMenuActivity extends AppCompatActivity {

    private ActivityProfileMenuBinding binding;
    private ShipperViewModel shipperViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);

        // Gọi API để lấy tên người dùng
        fetchShipperName();

        binding.imgBack.setOnClickListener(view -> {
            finish();
        });

        binding.btnLogout.setOnClickListener(view -> {
            logout();
        });

    }

    private void fetchShipperName() {
        shipperViewModel.getShipperName().observe(this, result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    // Optional: Show a loading indicator
                    break;
                case SUCCESS:
                    if (result.data != null && result.data.getFullName() != null) {
                        binding.tvUserName.setText(result.data.getFullName());
                    }
                    break;
                case ERROR:
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    public void logout() {
        new AlertDialog.Builder(ProfileMenuActivity.this)
                .setTitle("Xác nhận đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất khỏi ứng dụng không?")

                .setPositiveButton("Có", (dialog, which) -> {

                    TokenManager tokenManager = TokenManager.getInstance(ProfileMenuActivity.this);
                    tokenManager.clear();

                    Intent intent = new Intent(ProfileMenuActivity.this, LoginActivity.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                    finish();
                })

                .setNegativeButton("Không", (dialog, which) -> {
                    dialog.dismiss();
                })

                .show();
    }
}
