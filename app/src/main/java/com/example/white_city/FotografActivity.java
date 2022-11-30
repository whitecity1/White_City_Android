package com.example.white_city;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.white_city.Modelo.Fotografias;
import com.example.white_city.Retrofit_data.RetrofitApiService;
import com.example.white_city.Retrofit_data.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FotografActivity extends AppCompatActivity {

    RetrofitApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fotografia_layout);

        TextView idFotografia=(TextView)findViewById(R.id.Id); //Id para seleccionar un registro
        EditText txtId=(EditText)findViewById(R.id.txtId);     //Id para Editar un registro

        TextView nombre = (TextView) findViewById(R.id.nombre);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        TextView imagen = (TextView) findViewById(R.id.imagen);
        EditText txtImagen = ( EditText) findViewById(R.id.txtImagen);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

        Button btnGuardar=(Button)findViewById(R.id.btnGuardar);
        Button btnCancelar=(Button)findViewById(R.id.btnCancelar);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

        Bundle bundle=getIntent().getExtras(); //Variable y método para capturar los valores
        final String id = bundle.getString("ID");
        String txtnombre= bundle.getString("nombre");
        String txtimagen = bundle.getString("imagen");
        String txtdescripcion = bundle.getString("descripcion");

        txtId.setText(id);
        txtNombre.setText(txtnombre);
        txtImagen.setText(txtimagen);
        txtDescripcion.setText(txtdescripcion);

        if (id==null&&id.equals("")){
            idFotografia.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fotografias f=new Fotografias();
                f.setNombre(txtNombre.getText().toString());
                f.setImagen(txtImagen.getText().toString());
                f.setDescripcion(txtDescripcion.getText().toString());
                if (id.equals("")){
                    addFotografia(f);
                }else{

                    updateFotografia(f, Integer.valueOf(id));

                }


            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFotografia(Integer.valueOf(id));
                Intent intent =new Intent(FotografActivity.this,FotografiasActivity.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(FotografActivity.this,FotografiasActivity.class);
                startActivity(intent);
            }
        });
    }
    public void addFotografia(Fotografias f){
        service= RetrofitClient.getApiService();//Mirar si esta ben la conexion con el servicio
        Call<Fotografias>call=service.addFotografia(f);
        call.enqueue(new Callback<Fotografias>() {
            @Override
            public void onResponse(Call<Fotografias> call, Response<Fotografias> response) {
                if (response!=null){
                    Toast.makeText(FotografActivity.this, "Se agregó con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fotografias> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void updateFotografia(Fotografias f, int id){
        service= RetrofitClient.getApiService();//Mirar si esta ben la conexion con el servicio
        Call<Fotografias>call=service.updateFotografia(f, id);
        call.enqueue(new Callback<Fotografias>() {
            @Override
            public void onResponse(Call<Fotografias> call, Response<Fotografias> response) {
                if (response!=null){
                    Toast.makeText(FotografActivity.this, "Se actualizó con exito", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fotografias> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public void deleteFotografia(int id){
        service= RetrofitClient.getApiService();//Mirar si esta ben la conexion con el servicio
        Call<Fotografias>call=service.deleteFotografia(id);
        call.enqueue(new Callback<Fotografias>() {
            @Override
            public void onResponse(Call<Fotografias> call, Response<Fotografias> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FotografActivity.this, "Se eliminó el registro", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Fotografias> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
        Intent intent = new Intent(FotografActivity.this, FotografiasActivity.class);
        startActivity(intent);
    }
}
