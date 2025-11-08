package com.example.foodgodriver.View; // (Lưu ý: Đổi package cho đúng với dự án của bạn)

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodgodriver.ViewModel.AuthViewModel;
import com.example.foodgodriver.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Thiết lập ViewBinding
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Khởi tạo ViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.tvLoginNow.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        // 3. Xử lý sự kiện click nút Đăng ký
        binding.btnRegister.setOnClickListener(view -> handleRegister());
    }

    private void handleRegister() {
        // Lấy dữ liệu từ tất cả các EditText
        String name = binding.edtName.getText().toString().trim();
        String phone = binding.edtPhone.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();
        String passwordAgain = binding.edtPasswordAgain.getText().toString().trim();
        String licensePlate = binding.edtLicensePlate.getText().toString().trim();

        // 4. Kiểm tra (Validate) dữ liệu
        if (!validateInput(name, phone, password, passwordAgain, licensePlate)) {
            return; // Dừng lại nếu có lỗi
        }

        // 5. Hiển thị trạng thái đang tải
        setLoading(true);

        // 6. Gọi ViewModel để đăng ký
        authViewModel.register(phone, password, passwordAgain, name, licensePlate)
                .observe(this, result -> {
                    if (result == null) return;

                    switch (result.status) {
                        case LOADING:
                            // Đã xử lý ở trên
                            break;
                        case SUCCESS:
                            setLoading(false);
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                            // Chuyển về màn hình Đăng nhập sau khi đăng ký thành công
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            // Xóa các Activity trước đó khỏi stack
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                            break;
                        case ERROR:
                            setLoading(false);
                            // Hiển thị lỗi từ server (ví dụ: "SĐT đã tồn tại")
                            Toast.makeText(this, result.message, Toast.LENGTH_LONG).show();
                            break;
                    }
                });
    }

    /**
     * Hàm kiểm tra dữ liệu đầu vào
     * @return true nếu tất cả hợp lệ, false nếu có lỗi
     */
    private boolean validateInput(String name, String phone, String password, String passwordAgain, String licensePlate) {
        if (TextUtils.isEmpty(name)) {
            binding.edtName.setError("Vui lòng nhập họ tên");
            binding.edtName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(phone)) {
            binding.edtPhone.setError("Vui lòng nhập số điện thoại");
            binding.edtPhone.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            binding.edtPassword.setError("Vui lòng nhập mật khẩu");
            binding.edtPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            binding.edtPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            binding.edtPassword.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(passwordAgain)) {
            binding.edtPasswordAgain.setError("Vui lòng xác nhận mật khẩu");
            binding.edtPasswordAgain.requestFocus();
            return false;
        }

        if (!password.equals(passwordAgain)) {
            binding.edtPasswordAgain.setError("Mật khẩu không khớp");
            binding.edtPasswordAgain.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(licensePlate)) {
            binding.edtLicensePlate.setError("Vui lòng nhập biển số xe");
            binding.edtLicensePlate.requestFocus();
            return false;
        }

        return true; // Tất cả đều hợp lệ
    }

    // Hàm phụ trợ để khóa nút bấm khi đang tải
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            binding.btnRegister.setEnabled(false);
            binding.btnRegister.setText("Đang đăng ký...");
            // (Nếu bạn có ProgressBar, hãy hiện nó lên)
        } else {
            binding.btnRegister.setEnabled(true);
            binding.btnRegister.setText("Đăng kí");
            // (Ẩn ProgressBar đi)
        }
    }
}