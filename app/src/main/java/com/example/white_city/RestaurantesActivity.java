package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.white_city.databinding.ActivityDashboardBinding;
import com.example.white_city.databinding.ActivityRestaurantesBinding;

public class RestaurantesActivity extends DrawerBaseActivity {

    ActivityRestaurantesBinding activityRestaurantesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestaurantesBinding = ActivityRestaurantesBinding.inflate(getLayoutInflater());
        setContentView(activityRestaurantesBinding.getRoot());
        allocateActivityTitle("Restaurantes");
    }
}