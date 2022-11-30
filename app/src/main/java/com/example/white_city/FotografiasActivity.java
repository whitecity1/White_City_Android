package com.example.white_city;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.white_city.Adaptador.AdapterFotografia;
import com.example.white_city.Adaptador.AdapterFotografia;
import com.example.white_city.Modelo.Fotografias;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;
import com.example.white_city.databinding.ActivityFotografiasBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotografiasActivity extends DrawerBaseActivity  implements AdapterFotografia.OnQueryTextListener {

    private RecyclerView recyclerViewevFot;
    AdapterFotografia adapterFotografias;
    private ArrayList<Fotografias> fotografias;
    private RetrofitApiService retrofitApiService;
    private RetrofitClient retrofitClient;
    private SearchView svSearch;
    ActivityFotografiasBinding activityFotografiasBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFotografiasBinding = ActivityFotografiasBinding.inflate(getLayoutInflater());
        setContentView(activityFotografiasBinding.getRoot());

        allocateActivityTitle("Fotografias");
        recyclerViewevFot = findViewById(R.id.recyclerViewFoto);
        fotografias= new ArrayList<>();
        svSearch = findViewById(R.id.svSearch);
        retrofitApiService= RetrofitClient.getApiService();
        mostrarFotografias();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewevFot.setLayoutManager(layoutManager);

    }

    public void crear (View view) {
        Intent intent = new Intent(FotografiasActivity.this, FotografActivity.class);
        intent.putExtra("ID","");
        intent.putExtra("nombre", "");
        intent.putExtra("imagen", "");
        intent.putExtra("descripcion", "");
        startActivity(intent);
    }
    private void mostrarFotografias() {

        Call<List<Fotografias>> call= retrofitApiService.getFotografias();
        call.enqueue(new Callback<List<Fotografias>>() {


            @Override
            public void onResponse(Call<List<Fotografias>> call, Response<List<Fotografias>> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(FotografiasActivity.this, "Error codigo" +response.code(),Toast.LENGTH_SHORT).show();
                }

                List<Fotografias> fotografias= response.body();
                recyclerViewevFot.setLayoutManager(new LinearLayoutManager(FotografiasActivity.this));
                adapterFotografias= new AdapterFotografia((Context)FotografiasActivity.this, (ArrayList<Fotografias>)fotografias);
                recyclerViewevFot.setAdapter(adapterFotografias);

            }



            @Override
            public void onFailure(Call<List<Fotografias>> call, Throwable t) {

                Toast.makeText(FotografiasActivity.this, "Fallo en conexion" +t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }


}
