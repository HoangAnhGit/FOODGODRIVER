package com.example.foodgodriver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class ProfileMenuActivity extends AppCompatActivity {

    private Switch switchActive;
    private LinearLayout layoutMenu;

    private String[] menuTitles = {
            "Tự động nhận đơn", "Điểm đến của tôi", "HUB",
            "Thông báo", "Thu nhập", "Ví",
            "Lịch sử đơn hàng", "Trung tâm Trợ giúp", "Cài đặt"
    };

    private boolean[] hasDot = {true, false, false, true, false, true, false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_menu);

        switchActive = findViewById(R.id.switchActive);
        layoutMenu = findViewById(R.id.layoutMenu);

        setupMenuItems();
    }

    private void setupMenuItems() {
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < menuTitles.length; i++) {
            View item = inflater.inflate(R.layout.item_profile_menu, layoutMenu, false);

            TextView tvTitle = item.findViewById(R.id.tvTitle);
            View dotView = item.findViewById(R.id.dotView);

            tvTitle.setText(menuTitles[i]);
            dotView.setVisibility(hasDot[i] ? View.VISIBLE : View.GONE);

            // Sự kiện click
            int index = i;
            item.setOnClickListener(v -> {
                Toast.makeText(this, "Chọn: " + menuTitles[index], Toast.LENGTH_SHORT).show();
            });

            layoutMenu.addView(item);
        }
    }
}
