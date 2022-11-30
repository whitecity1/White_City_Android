package com.example.white_city;

import android.os.Bundle;

import com.example.white_city.databinding.ActivityDashboardBinding;
import com.example.white_city.databinding.ActivityRecomendadosBinding;

public class RecomendadosActivity extends DrawerBaseActivity {

 ActivityRecomendadosBinding activityRecomendadosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRecomendadosBinding=ActivityRecomendadosBinding.inflate(getLayoutInflater());
        setContentView(activityRecomendadosBinding.getRoot());
        allocateActivityTitle("Recomendados");

    }

}