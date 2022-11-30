package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.white_city.Adaptador.AdapterRutasturisticas;
import com.example.white_city.Modelo.Rutaturistica;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityRutasturisticasBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RutasturisticasActivity extends DrawerBaseActivity {

    private RecyclerView recyclerViewRutaturistica;
    private AdapterRutasturisticas adapterRutasturisticas;
    private ArrayList<Rutaturistica> rutasturisticas;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;

    ActivityRutasturisticasBinding activityRutasturisticasBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRutasturisticasBinding = ActivityRutasturisticasBinding.inflate(getLayoutInflater());
        setContentView(activityRutasturisticasBinding.getRoot());
        allocateActivityTitle("Rutas Turisticas");

        recyclerViewRutaturistica=findViewById(R.id.recyclerViewRutaturistica);
        rutasturisticas=new ArrayList<>();
        svSearch =findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarRutasturisticas();
    }


    private  void mostrarRutasturisticas(){
        Call<List<Rutaturistica>> call= retrofitApiService.getRutasturisticas();
        call.enqueue(new Callback<List<Rutaturistica>>() {
            @Override
            public void onResponse(Call<List<Rutaturistica>> call,
                                   Response<List<Rutaturistica>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RutasturisticasActivity.this,"Error codigo" +response.code(),Toast.LENGTH_LONG).show();
                }
                List<Rutaturistica> rutasturisticas=response.body();
                recyclerViewRutaturistica.setLayoutManager(new LinearLayoutManager(RutasturisticasActivity.this));
                adapterRutasturisticas=new AdapterRutasturisticas((Context) RutasturisticasActivity.this, (ArrayList<Rutaturistica>) rutasturisticas);

                recyclerViewRutaturistica.setAdapter(adapterRutasturisticas);

            }

            @Override
            public void onFailure(Call<List<Rutaturistica>> call, Throwable t) {
                Toast.makeText(RutasturisticasActivity.this,"fallo en contexion"
                        +t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

}