package com.example.foodgodriver.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgodriver.R;
import com.example.foodgodriver.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Gắn adapter cho ViewPager2
        OrdersPagerAdapter adapter = new OrdersPagerAdapter(this);
        binding.viewPagerOrders.setAdapter(adapter);

        // Kết nối TabLayout và ViewPager2
        new TabLayoutMediator(binding.tabLayoutOrders, binding.viewPagerOrders,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("ĐANG LÀM");
                    } else {
                        tab.setText("FREE-PICK");
                    }
                }).attach();

        // Nút chuyển trạng thái
        binding.btnToggleStatus.setOnClickListener(v -> {
            String current = binding.btnToggleStatus.getText().toString();
            if (current.equals("Sẵn sàng hoạt động")) {
                binding.btnToggleStatus.setText("Tạm nghỉ");
                binding.btnToggleStatus.setBackgroundTintList(
                        getResources().getColorStateList(android.R.color.darker_gray)
                );
            } else {
                binding.btnToggleStatus.setText("Sẵn sàng hoạt động");
                binding.btnToggleStatus.setBackgroundTintList(
                        getResources().getColorStateList(R.color.colorPrimary)
                );
            }
        });
    }
    }
