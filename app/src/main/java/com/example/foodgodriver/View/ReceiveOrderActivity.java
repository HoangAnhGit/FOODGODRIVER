package com.example.foodgodriver.View;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgodriver.R;

public class ReceiveOrderActivity extends AppCompatActivity {

    // Tên biến được đổi để phù hợp với ID mới
    Button btnCompleteOrder;
    
    // Logic đếm ngược không còn phù hợp với nút "Hoàn thành đơn hàng"
    // int timeLeft = 30;
    // Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_order);

        // ID của nút đã được thay đổi trong tệp XML
        btnCompleteOrder = findViewById(R.id.btn_complete_order);
        
        // Xóa bỏ lời gọi hàm đếm ngược không còn sử dụng
        // startCountdown();
    }

    /*
    private void startCountdown() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    btnCompleteOrder.setText("Chấp nhận (" + timeLeft + "s)");
                    handler.postDelayed(this, 1000);
                } else {
                    btnCompleteOrder.setEnabled(false);
                    btnCompleteOrder.setText("Hết thời gian");
                }
            }
        }, 1000);
    }
    */
}
