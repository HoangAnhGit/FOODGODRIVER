package com.example.foodgodriver;

// FIXME: This Activity is likely unused. The main logic is in `FreePickFragment.java`.
// This file can probably be deleted.

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgodriver.Adapter.FreePickAdapter;
import com.example.foodgodriver.ViewModel.ShipperViewModel;

import java.util.ArrayList;

// 1. Implement the listener interface
public class FreePickActivity extends AppCompatActivity implements FreePickAdapter.OnOrderAcceptListener {

    private ShipperViewModel shipperViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_pick);

        Log.d("FreePickActivity", "onCreate: Bắt đầu.");

        RecyclerView recyclerView = findViewById(R.id.recyclerViewFreePick);
        if (recyclerView == null) {
            Log.e("FreePickActivity", "onCreate: Lỗi! RecyclerView không được tìm thấy. Hãy kiểm tra lại tệp layout activity_free_pick.xml.");
            return;
        }
        Log.d("FreePickActivity", "onCreate: RecyclerView đã được tìm thấy.");

        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);
        
        // 2. Update the constructor call to pass 'this' as the listener
        FreePickAdapter adapter = new FreePickAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);
        Log.d("FreePickActivity", "onCreate: Adapter đã được gán thành công cho RecyclerView.");
    }

    // 3. Implement the interface method
    @Override
    public void onOrderAccept(long orderId) {
        // This is a placeholder implementation since this activity is likely unused.
        shipperViewModel.pickOrder(orderId).observe(this, result -> {
            if (result != null && result.status == com.example.foodgodriver.Utils.Result.Status.SUCCESS) {
                Toast.makeText(this, "Đã nhận đơn (Activity không sử dụng)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
