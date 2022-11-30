package com.example.white_city;



import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.Adaptador.AdapterEventos;
import com.example.white_city.Modelo.Evento;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityDashboardBinding;
import com.example.white_city.databinding.ActivityEventosBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosActivity extends DrawerBaseActivity {
    private RecyclerView recyclerViewEvento;
    private AdapterEventos adapterEventos;
    private ArrayList<Evento> eventos;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityEventosBinding activityEventosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEventosBinding =  ActivityEventosBinding.inflate(getLayoutInflater());
        setContentView( activityEventosBinding.getRoot());
        allocateActivityTitle("Eventos");

        recyclerViewEvento=findViewById(R.id.recyclerViewEvento);
        eventos=new ArrayList<>();
        svSearch =findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarEventos();
    }

    private  void mostrarEventos(){
        Call<List<Evento>> call= retrofitApiService.getEventos();
        call.enqueue(new Callback<List<Evento>>() {
            @Override
            public void onResponse(Call<List<Evento>> call,
                                   Response<List<Evento>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(EventosActivity.this,"Error codigo" +response.code(),Toast.LENGTH_LONG).show();
                }
                List<Evento> eventos=response.body();
                recyclerViewEvento.setLayoutManager(new LinearLayoutManager(EventosActivity.this));
                adapterEventos=new AdapterEventos((Context) EventosActivity.this, (ArrayList<Evento>) eventos);

                recyclerViewEvento.setAdapter(adapterEventos);

            }

            @Override
            public void onFailure(Call<List<Evento>> call, Throwable t) {
                Toast.makeText(EventosActivity.this,"fallo en contexion"
                        +t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}