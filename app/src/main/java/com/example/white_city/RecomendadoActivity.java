package com.example.white_city;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.Adaptador.AdapterRecomendado;
import com.example.white_city.Modelo.Recomendado;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityRecomendadoBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecomendadoActivity extends DrawerBaseActivity {
    private RecyclerView recyclerViewRecomendado;
    private AdapterRecomendado adapterRecomendado;
    private ArrayList<Recomendado> recomendados;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityRecomendadoBinding activityRecomendadoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRecomendadoBinding = ActivityRecomendadoBinding.inflate(getLayoutInflater());
        setContentView(activityRecomendadoBinding.getRoot());
        allocateActivityTitle("Recomendados");

        recyclerViewRecomendado = findViewById(R.id.recyclerViewRecomendado);
        recomendados = new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService = RetrofitClient.getApiService();
        mostrarRecomendados();
    }

    private void mostrarRecomendados() {
        Call<List<Recomendado>> call = retrofitApiService.getRecomendado();
        call.enqueue(new Callback<List<Recomendado>>() {
            @Override
            public void onResponse(Call<List<Recomendado>> call,
                                   Response<List<Recomendado>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RecomendadoActivity.this, "Error codigo" + response.code(), Toast.LENGTH_LONG).show();
                }
                List<Recomendado> recomendados = response.body();
                recyclerViewRecomendado.setLayoutManager(new LinearLayoutManager(RecomendadoActivity.this));
                adapterRecomendado = new AdapterRecomendado((Context) RecomendadoActivity.this, (ArrayList<Recomendado>) recomendados);

                recyclerViewRecomendado.setAdapter(adapterRecomendado);

            }

            @Override
            public void onFailure(Call<List<Recomendado>> call, Throwable t) {
                Toast.makeText(RecomendadoActivity.this, "fallo en contexion"
                        + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}

