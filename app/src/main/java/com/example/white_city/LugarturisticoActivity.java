package com.example.white_city;



import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.Adaptador.AdapterLugarturistico;
import com.example.white_city.Modelo.Lugarturistico;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityLugarturisticoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LugarturisticoActivity extends DrawerBaseActivity {
    private RecyclerView recyclerViewLugarest;
    private AdapterLugarturistico adapterLugarturistico;
    private ArrayList<Lugarturistico> lugarturistico;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityLugarturisticoBinding activityLugarturisticoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLugarturisticoBinding = ActivityLugarturisticoBinding.inflate(getLayoutInflater());
        setContentView(activityLugarturisticoBinding.getRoot());
        allocateActivityTitle("Lugares Turisticos");


        recyclerViewLugarest=findViewById(R.id.recyclerViewLugarest);
        lugarturistico=new ArrayList<>();
        svSearch =findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarLugarturistico();
    }

    private  void mostrarLugarturistico(){
        Call<List<Lugarturistico>> call= retrofitApiService.getLugarturistico();
        call.enqueue(new Callback<List<Lugarturistico>>() {
            @Override
            public void onResponse(Call<List<Lugarturistico>> call,
                                   Response<List<Lugarturistico>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(LugarturisticoActivity.this,"Error codigo" +response.code(),Toast.LENGTH_LONG).show();
                }
                List<Lugarturistico> lugarturisticos=response.body();
                recyclerViewLugarest.setLayoutManager(new LinearLayoutManager(LugarturisticoActivity.this));
                adapterLugarturistico=new AdapterLugarturistico((Context) LugarturisticoActivity.this, (ArrayList<Lugarturistico>) lugarturisticos);

                recyclerViewLugarest.setAdapter(adapterLugarturistico);

            }

            @Override
            public void onFailure(Call<List<Lugarturistico>> call, Throwable t) {
                Toast.makeText(LugarturisticoActivity.this,"fallo en contexion"
                        +t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}