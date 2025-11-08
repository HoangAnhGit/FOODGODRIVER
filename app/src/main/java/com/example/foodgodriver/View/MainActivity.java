package com.example.foodgodriver.View;

// 1. XÓA BỎ IMPORT SAI: import static java.security.AccessController.getContext;

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

            // 3. Tạo và hiển thị Dialog (Sửa context thành 'this' cho rõ ràng)
            new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(positiveButtonText, (dialog, which) -> {
                        // --- 2. SỬA LỖI LOGIC: Thêm lệnh gọi API ---
                        boolean newStatus = !isCurrentlyAvailable;
                        callApiToUpdateStatus(newStatus); // <-- GỌI HÀM CẬP NHẬT
                        // ------------------------------------
                    })
                    .setNegativeButton("Hủy", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }

    private void callApiToUpdateStatus(boolean newStatus) {
        // 1. Hiển thị trạng thái đang tải
        setLoading(true);

        // 2. Gọi ViewModel (SỬA LỖI 3: Dùng 'this' thay vì 'getViewLifecycleOwner()')
        shipperViewModel.updateAvailability(newStatus).observe(this, result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    // Đã xử lý ở setLoading(true)
                    break;
                case SUCCESS:
                    // 3. API THÀNH CÔNG -> Cập nhật UI
                    setLoading(false); // Mở khóa nút

                    // Lấy trạng thái chính xác từ server trả về
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

                    // (SỬA LỖI 3: Dùng 'this' hoặc 'MainActivity.this' làm Context)
                    Toast.makeText(MainActivity.this, result.data.getMessage(), Toast.LENGTH_SHORT).show();
                    break;

                case ERROR:
                    // 4. API THẤT BẠI -> Không thay đổi UI
                    setLoading(false); // Mở khóa nút

                    // (SỬA LỖI 3: Dùng 'this' hoặc 'MainActivity.this' làm Context)
                    Toast.makeText(MainActivity.this, result.message, Toast.LENGTH_SHORT).show();
                    // (Nút bấm vẫn giữ nguyên trạng thái cũ vì API thất bại)
                    break;
            }

            // (SỬA LỖI 3: Dùng 'this' thay vì 'getViewLifecycleOwner()')
            shipperViewModel.updateAvailability(newStatus).removeObservers(this);
        });
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            binding.btnToggleStatus.setEnabled(false);
            binding.btnToggleStatus.setText("Đang cập nhật...");
        } else {
            binding.btnToggleStatus.setEnabled(true);
            // (Không cần setText ở đây, vì khối 'SUCCESS' sẽ tự đặt text)
        }
    }
}