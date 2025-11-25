package com.example.foodgodriver.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgodriver.Network.DTO.FreePickOrder;
import com.example.foodgodriver.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FreePickAdapter extends RecyclerView.Adapter<FreePickAdapter.FreePickViewHolder> {

    private List<FreePickOrder> orderList;
    private OnOrderAcceptListener listener;

    // 1. Interface để lắng nghe sự kiện click
    public interface OnOrderAcceptListener {
        void onOrderAccept(long orderId);
    }

    public FreePickAdapter(List<FreePickOrder> orderList, OnOrderAcceptListener listener) {
        this.orderList = orderList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FreePickViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_free_pick, parent, false);
        return new FreePickViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FreePickViewHolder holder, int position) {
        FreePickOrder order = orderList.get(position);
        holder.bind(order);
        // 2. Khi nút được nhấn, gọi listener
        holder.btnAcceptOrder.setOnClickListener(v -> {
            if (listener != null) {
                listener.onOrderAccept(order.getOrderId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void setOrderList(List<FreePickOrder> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    static class FreePickViewHolder extends RecyclerView.ViewHolder {
        ImageView imgShopLogo;
        TextView tvShopName, tvDistance, tvMainDish, tvMoreItems, tvDestination, tvTotalPrice, tvIncome;
        Button btnAcceptOrder;

        public FreePickViewHolder(@NonNull View itemView) {
            super(itemView);
            imgShopLogo = itemView.findViewById(R.id.imgShopLogo);
            tvShopName = itemView.findViewById(R.id.tvShopName);
            tvDistance = itemView.findViewById(R.id.tvDistance);
            tvMainDish = itemView.findViewById(R.id.tvMainDish);
            tvMoreItems = itemView.findViewById(R.id.tvMoreItems);
            tvDestination = itemView.findViewById(R.id.tvDestination);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvIncome = itemView.findViewById(R.id.tvIncome);
            btnAcceptOrder = itemView.findViewById(R.id.btnAcceptOrder);
        }

        public void bind(FreePickOrder order) {
            tvShopName.setText(order.getShopName());
            tvDistance.setText(order.getDistance());
            tvMainDish.setText(order.getMainDish());
            tvMoreItems.setText(order.getMoreItems());
            tvDestination.setText(order.getDestination());
            tvTotalPrice.setText(order.getTotalPrice());
            tvIncome.setText(order.getIncome());

            if (order.getShopLogoUrl() != null && !order.getShopLogoUrl().isEmpty()) {
                Picasso.get().load(order.getShopLogoUrl()).into(imgShopLogo);
            }
        }
    }
}
