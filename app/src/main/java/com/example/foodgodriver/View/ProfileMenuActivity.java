package com.example.foodgodriver.View;

// Bỏ dòng import này đi, nó sai và không cần thiết
// import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgodriver.Network.TokenManager;
import com.example.foodgodriver.R;
import com.example.foodgodriver.databinding.ActivityProfileMenuBinding;

public class ProfileMenuActivity extends AppCompatActivity {

    private ActivityProfileMenuBinding binding;

    private boolean[] hasDot = {true, false, false, true, false, true, false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(view -> {
            finish();
        });

        binding.btnLogout.setOnClickListener(view -> {
            logout();
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