package com.example.foodgodriver.View;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgodriver.Adapter.OrderItemAdapter;
import com.example.foodgodriver.Network.DTO.OrderItemsResponse;
import com.example.foodgodriver.R;
import com.example.foodgodriver.ViewModel.ShipperViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetailOrderActivity extends AppCompatActivity {

    private ShipperViewModel shipperViewModel;
    private OrderItemAdapter adapter;
    private long orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        orderId = getIntent().getLongExtra("ORDER_ID", -1);
        if (orderId == -1) {
            Toast.makeText(this, "Error: Invalid Order ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rv_order_items);
        adapter = new OrderItemAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        fetchOrderItems();
    }

    private void fetchOrderItems() {
        shipperViewModel.getOrderItems(orderId).observe(this, result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    OrderItemsResponse response = result.data;
                    if (response != null) {
                        updateUI(response);
                    } else {
                        Toast.makeText(this, "No items found for this order.", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case ERROR:
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void updateUI(OrderItemsResponse response) {
        TextView tvRestaurantName = findViewById(R.id.tv_restaurant_name);
        TextView tvSubtotal = findViewById(R.id.tv_subtotal);
        TextView tvShippingFee = findViewById(R.id.tv_shipping_fee);
        TextView tvTotalAmount = findViewById(R.id.tv_total_amount);

        DecimalFormat formatter = new DecimalFormat("#,###");

        tvRestaurantName.setText(response.getRestaurantName());
        tvSubtotal.setText("Subtotal: " + formatter.format(response.getSubtotal()) + "đ");
        tvShippingFee.setText("Shipping Fee: " + formatter.format(response.getShippingFee()) + "đ");
        tvTotalAmount.setText("Total: " + formatter.format(response.getTotalAmount()) + "đ");

        if (response.getItems() != null) {
            adapter.setItemList(response.getItems());
        }
    }
}
