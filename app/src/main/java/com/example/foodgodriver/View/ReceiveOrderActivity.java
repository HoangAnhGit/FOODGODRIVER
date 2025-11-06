package com.example.foodgodriver.View;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgodriver.R;

public class ReceiveOrderActivity extends AppCompatActivity {


    Button btnAccept;
    int timeLeft = 30;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_order);

        btnAccept = findViewById(R.id.btn_accept);
        startCountdown();
    }

    private void startCountdown() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timeLeft > 0) {
                    timeLeft--;
                    btnAccept.setText("Chấp nhận (" + timeLeft + "s)");
                    handler.postDelayed(this, 1000);
                } else {
                    btnAccept.setEnabled(false);
                    btnAccept.setText("Hết thời gian");
                }
            }
        }, 1000);
    }
}