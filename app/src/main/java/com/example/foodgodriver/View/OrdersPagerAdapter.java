package com.example.foodgodriver.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OrdersPagerAdapter  extends FragmentStateAdapter {
    public OrdersPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new DoingOrdersFragment(); // Tab "Đang làm"
        } else {
            // Trả về FreePickFragment cho tab "FREE-PICK"
            return new FreePickFragment(); 
        }
    }

    @Override
    public int getItemCount() {
        // Chúng ta có 2 tab
        return 2;
    }
}
