package com.example.foodgodriver.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgodriver.Adapter.FreePickAdapter;
import com.example.foodgodriver.Network.DTO.FreePickOrder;
import com.example.foodgodriver.R;
import com.example.foodgodriver.ViewModel.ShipperViewModel;

import java.util.ArrayList;
import java.util.List;

public class FreePickFragment extends Fragment implements FreePickAdapter.OnOrderAcceptListener {

    private ShipperViewModel shipperViewModel;
    private RecyclerView recyclerView;
    private FreePickAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvMessage;

    private enum ViewState { LOADING, SUCCESS, ERROR }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shipperViewModel = new ViewModelProvider(this).get(ShipperViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_free_pick, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFreePick);
        progressBar = view.findViewById(R.id.progressBar);
        tvMessage = view.findViewById(R.id.tv_message);

        adapter = new FreePickAdapter(new ArrayList<>(), this);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchFreePickOrders();
    }

    private void fetchFreePickOrders() {
        showState(ViewState.LOADING, null);
        shipperViewModel.getFreePickOrders().observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    showState(ViewState.LOADING, null);
                    break;
                case SUCCESS:
                    List<FreePickOrder> orders = result.data.getData();
                    if (orders != null && !orders.isEmpty()) {
                        adapter.setOrderList(orders);
                        showState(ViewState.SUCCESS, null);
                    } else {
                        showState(ViewState.ERROR, "Không có đơn hàng nào.");
                    }
                    break;
                case ERROR:
                    showState(ViewState.ERROR, result.message);
                    break;
            }
        });
    }

    @Override
    public void onOrderAccept(long orderId) {
        shipperViewModel.pickOrder(orderId).observe(getViewLifecycleOwner(), result -> {
            if (result == null) return;

            switch (result.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    Toast.makeText(getContext(), "Nhận đơn thành công!", Toast.LENGTH_SHORT).show();
                    fetchFreePickOrders();
                    break;
                case ERROR:
                    Toast.makeText(getContext(), result.message, Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void showState(ViewState state, String message) {
        progressBar.setVisibility(state == ViewState.LOADING ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(state == ViewState.SUCCESS ? View.VISIBLE : View.GONE);
        tvMessage.setVisibility(state == ViewState.ERROR ? View.VISIBLE : View.GONE);
        if (state == ViewState.ERROR) {
            tvMessage.setText(message);
        }
    }
}
