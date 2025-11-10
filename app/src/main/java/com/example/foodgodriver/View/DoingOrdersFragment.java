package com.example.foodgodriver.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodgodriver.Network.DTO.CurrentDeliveryData;
import com.example.foodgodriver.R;
import com.example.foodgodriver.ViewModel.ShipperViewModel;

import java.text.DecimalFormat;

public class DoingOrdersFragment extends Fragment {

    private ShipperViewModel shipperViewModel;
    private LinearLayout layoutTamNghi;
    private LinearLayout layoutDanhSachDon;
    private View includedOrderLayout;
    private CurrentDeliveryData currentOrderData; // Store current order data

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doing_orders, container, false);

        layoutTamNghi = view.findViewById(R.id.layout_tam_nghi);
        layoutDanhSachDon = view.findViewById(R.id.layout_danh_sach_don);
        includedOrderLayout = view.findViewById(R.id.included_order_layout);

        // Set click listener to open detail activity
        includedOrderLayout.setOnClickListener(v -> {
            if (currentOrderData != null) {
                Intent intent = new Intent(getActivity(), DetailOrderActivity.class);
                intent.putExtra("ORDER_ID", currentOrderData.getOrderId());
                startActivity(intent);
            }
        });

        Button btnCompleteOrder = includedOrderLayout.findViewById(R.id.btn_complete_order);
        btnCompleteOrder.setOnClickListener(v -> {
            if (currentOrderData != null) {
                completeOrder(currentOrderData.getOrderId());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCurrentDelivery();
    }

    private void fetchCurrentDelivery() {
        shipperViewModel.getCurrentDelivery().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    currentOrderData = result.data.getData(); // Store the data
                    if (currentOrderData != null) {
                        layoutTamNghi.setVisibility(View.GONE);
                        layoutDanhSachDon.setVisibility(View.VISIBLE);
                        bindDataToView(currentOrderData);
                    } else {
                        layoutTamNghi.setVisibility(View.VISIBLE);
                        layoutDanhSachDon.setVisibility(View.GONE);
                    }
                    break;
                case ERROR:
                    layoutTamNghi.setVisibility(View.VISIBLE);
                    layoutDanhSachDon.setVisibility(View.GONE);
                    Toast.makeText(getContext(), result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void bindDataToView(CurrentDeliveryData data) {
        if (includedOrderLayout == null) return;

        TextView tvAmount = includedOrderLayout.findViewById(R.id.tv_amount);
        TextView tvSurchargeReason = includedOrderLayout.findViewById(R.id.surcharge_reason);
        TextView tvOrderCode = includedOrderLayout.findViewById(R.id.order_code);
        TextView tvDistance = includedOrderLayout.findViewById(R.id.tv_distance);
        TextView tvRestaurantName = includedOrderLayout.findViewById(R.id.restaurant_name);
        TextView tvRestaurantAddress = includedOrderLayout.findViewById(R.id.restaurant_address);
        TextView tvEstimatedPickupTime = includedOrderLayout.findViewById(R.id.estimated_pickup_time);
        TextView tvCustomerName = includedOrderLayout.findViewById(R.id.customer_name);
        TextView tvDeliveryAddress = includedOrderLayout.findViewById(R.id.delivery_address);
        TextView tvEstimatedDeliveryTime = includedOrderLayout.findViewById(R.id.estimated_delivery_time);

        DecimalFormat formatter = new DecimalFormat("#,###");

        double totalIncome = data.getShipperIncome() + data.getSurchargeFee();
        tvAmount.setText(formatter.format(totalIncome) + "đ");

        tvSurchargeReason.setText(data.getSurchargeReason() + ": " + formatter.format(data.getSurchargeFee()) + "đ");
        tvOrderCode.setText(data.getOrderCode());
        tvDistance.setText("Tổng cộng " + data.getTotalDistanceKm() + " km");
        tvRestaurantName.setText(data.getRestaurantName());
        tvRestaurantAddress.setText(data.getRestaurantAddress());
        tvEstimatedPickupTime.setText("Lấy hàng lúc: " + data.getEstimatedPickupTime());
        tvCustomerName.setText(data.getCustomerName());
        tvDeliveryAddress.setText(data.getDeliveryAddress());
        tvEstimatedDeliveryTime.setText("Giao hàng lúc: " + data.getEstimatedDeliveryTime());
    }

    private void completeOrder(long orderId) {
        shipperViewModel.completeOrder(orderId).observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    // Optional: Show a progress bar or disable the button
                    break;
                case SUCCESS:
                    Toast.makeText(getContext(), "Order completed successfully!", Toast.LENGTH_SHORT).show();
                    // Refresh the view
                    fetchCurrentDelivery(); 
                    break;
                case ERROR:
                    Toast.makeText(getContext(), result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}
