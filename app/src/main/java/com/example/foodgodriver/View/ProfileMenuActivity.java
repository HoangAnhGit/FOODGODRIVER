package com.example.foodgodriver.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgodriver.R;
import com.example.foodgodriver.databinding.ActivityProfileMenuBinding;

public class ProfileMenuActivity extends AppCompatActivity {


    private ActivityProfileMenuBinding binding;


    private boolean[] hasDot = {true, false, false, true, false, true, false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.imgBack.setOnClickListener(view -> {finish();});




    }



}
