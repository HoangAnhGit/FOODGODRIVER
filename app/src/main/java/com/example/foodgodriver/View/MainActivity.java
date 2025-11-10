package com.example.foodgodriver.View;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodgodriver.R;
import com.example.foodgodriver.ViewModel.ShipperViewModel;
import com.example.foodgodriver.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ShipperViewModel shipperViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);

        // Lấy và hiển thị tên shipper
        fetchShipperName();

        binding.layoutMenu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfileMenuActivity.class);
            startActivity(intent);
        });
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
            String currentText = binding.btnToggleStatus.getText().toString();
            boolean isCurrentlyAvailable = currentText.equals("Sẵn sàng hoạt động");
            String title, message, positiveButtonText;

            if (isCurrentlyAvailable) {
                title = "Xác nhận tạm nghỉ";
                message = "Bạn có chắc chắn muốn tạm nghỉ không? Bạn sẽ không nhận được đơn hàng mới.";
                positiveButtonText = "Tạm nghỉ";
            } else {
                title = "Sẵn sàng hoạt động";
                message = "Bạn đã sẵn sàng để nhận đơn hàng mới?";
                positiveButtonText = "Sẵn sàng";
            }

            new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText, (dialog, which) -> {
                        boolean newStatus = !isCurrentlyAvailable;
                        callApiToUpdateStatus(newStatus);
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }

    private void fetchShipperName() {
        shipperViewModel.getShipperName().observe(this, result -> {
            if (result == null || result.data == null) return;
            if (result.status == com.example.foodgodriver.Utils.Result.Status.SUCCESS) {
                binding.tvName.setText(result.data.getFullName());
            }
            // Optional: Handle ERROR case
        });
    }

    private void callApiToUpdateStatus(boolean newStatus) {
        setLoading(true);
        shipperViewModel.updateAvailability(newStatus).observe(this, result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    setLoading(false);
                    boolean isAvailableNow = result.data.isAvailable();
                    if (isAvailableNow) {
                        binding.btnToggleStatus.setText("Sẵn sàng hoạt động");
                        binding.btnToggleStatus.setBackgroundTintList(
                                getResources().getColorStateList(R.color.colorPrimary)
                        );
                    } else {
                        binding.btnToggleStatus.setText("Tạm nghỉ");
                        binding.btnToggleStatus.setBackgroundTintList(
                                getResources().getColorStateList(android.R.color.darker_gray)
                        );
                    }
                    Toast.makeText(MainActivity.this, result.data.getMessage(), Toast.LENGTH_SHORT).show();
                    break;

                case ERROR:
                    setLoading(false);
                    Toast.makeText(MainActivity.this, result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
            shipperViewModel.updateAvailability(newStatus).removeObservers(this);
        });
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            binding.btnToggleStatus.setEnabled(false);
            binding.btnToggleStatus.setText("Đang cập nhật...");
        } else {
            binding.btnToggleStatus.setEnabled(true);
        }
    }
}
