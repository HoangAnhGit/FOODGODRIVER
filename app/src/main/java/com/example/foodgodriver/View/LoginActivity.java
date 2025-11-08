package com.example.foodgodriver.View; // (Lưu ý: Đổi package cho đúng với dự án của bạn)

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.foodgodriver.ViewModel.AuthViewModel;
import com.example.foodgodriver.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AuthViewModel authViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Thiết lập ViewBinding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 2. Khởi tạo ViewModel
        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        binding.tvRegisterNow.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        // 3. Xử lý sự kiện click nút Đăng nhập
        binding.btnLogin.setOnClickListener(view -> handleLogin());
    }

    private void handleLogin() {
        // Lấy dữ liệu từ EditText
        String phone = binding.edtPhone.getText().toString().trim();
        String password = binding.edtPassword.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào (Validate)
        if (TextUtils.isEmpty(phone)) {
            binding.edtPhone.setError("Vui lòng nhập số điện thoại");
            binding.edtPhone.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            binding.edtPassword.setError("Vui lòng nhập mật khẩu");
            binding.edtPassword.requestFocus();
            return;
        }

        // Hiển thị trạng thái đang xử lý (Optional: Bạn có thể thêm ProgressBar vào layout để đẹp hơn)
        setLoading(true);

        // Gọi ViewModel để đăng nhập
        // Lưu ý: Chúng ta observe (quan sát) kết quả trả về
        authViewModel.login(phone, password).observe(this, result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    // Đã xử lý ở trên hoặc bạn có thể xử lý ở đây
                    break;
                case SUCCESS:
                    setLoading(false);
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                    // Chuyển sang màn hình chính (MainActivity)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    // Xóa cờ để người dùng không back lại được màn hình login
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    break;
                case ERROR:
                    setLoading(false);
                    Toast.makeText(LoginActivity.this, result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    // Hàm phụ trợ để khóa nút bấm khi đang tải
    private void setLoading(boolean isLoading) {
        if (isLoading) {
            binding.btnLogin.setEnabled(false);
            binding.btnLogin.setText("Đang xử lý...");
            // Nếu layout có ProgressBar thì hiện nó lên: binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.btnLogin.setEnabled(true);
            binding.btnLogin.setText("Đăng nhập");
            // binding.progressBar.setVisibility(View.GONE);
        }
    }
}