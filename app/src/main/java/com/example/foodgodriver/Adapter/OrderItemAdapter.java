package com.example.foodgodriver.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodgodriver.Network.DTO.OrderItem;
import com.example.foodgodriver.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {

    private List<OrderItem> itemList;

    public OrderItemAdapter(List<OrderItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemViewHolder holder, int position) {
        OrderItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(List<OrderItem> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    static class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodName, tvFoodQuantity, tvFoodPrice;

        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tv_food_name);
            tvFoodQuantity = itemView.findViewById(R.id.tv_food_quantity);
            tvFoodPrice = itemView.findViewById(R.id.tv_food_price);
        }

        public void bind(OrderItem item) {
            tvFoodName.setText(item.getDishName());
            tvFoodQuantity.setText("x" + item.getQuantity());

            DecimalFormat formatter = new DecimalFormat("#,###");
            String formattedPrice = formatter.format(item.getLineTotal());
            tvFoodPrice.setText(formattedPrice + "đ");
        }
    }
}
