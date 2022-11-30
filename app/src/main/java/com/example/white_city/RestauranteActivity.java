package com.example.white_city;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.Adaptador.AdapterRestaurantes;
import com.example.white_city.Modelo.Restaurante;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityRestauranteBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestauranteActivity extends DrawerBaseActivity {

    private RecyclerView recyclerViewRestaurante;
    private AdapterRestaurantes adapterRestaurantes;
    private ArrayList<Restaurante> restaurantes;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityRestauranteBinding activityRestauranteBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRestauranteBinding = ActivityRestauranteBinding.inflate(getLayoutInflater());
        setContentView(activityRestauranteBinding.getRoot());
        allocateActivityTitle("Restaurantes");

        recyclerViewRestaurante=findViewById(R.id.recyclerViewRestaurante);
        restaurantes=new ArrayList<>();
        svSearch =findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarRestaurantes();
    }


    private  void mostrarRestaurantes(){
        Call<List<Restaurante>> call= retrofitApiService.getRestaurantes();
        call.enqueue(new Callback<List<Restaurante>>() {
            @Override
            public void onResponse(Call<List<Restaurante>> call,
                                   Response<List<Restaurante>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RestauranteActivity.this,"Error codigo" +response.code(),Toast.LENGTH_LONG).show();
                }
                List<Restaurante>restaurantes=response.body();
                recyclerViewRestaurante.setLayoutManager(new LinearLayoutManager(RestauranteActivity.this));
                adapterRestaurantes=new AdapterRestaurantes((Context) RestauranteActivity.this, (ArrayList<Restaurante>) restaurantes);

                recyclerViewRestaurante.setAdapter(adapterRestaurantes);

            }

            @Override
            public void onFailure(Call<List<Restaurante>> call, Throwable t) {
                Toast.makeText(RestauranteActivity.this,"fallo en contexion"
                        +t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }



}