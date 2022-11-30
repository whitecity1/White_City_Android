package com.example.white_city;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.white_city.databinding.ActivityNuevaBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class nuevaActivity extends DrawerBaseActivity {

    ActivityNuevaBinding activityNuevaBinding;

    EditText etIdBuscar, etId, etNombres, etApellidos, etEmail,etPassword;
    Button btnIdBuscar, btnEliminar, btnTodosBuscar, btnAgregar, btnEditar;

    RecyclerView rvUsuarios;
    List<Usuario> listaUsuarios = new ArrayList<>();

    AdaptadorUsuarios adaptadorUsuarios;

    Retrofit retrofit;
    APIRest api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNuevaBinding = ActivityNuevaBinding.inflate(getLayoutInflater());
        allocateActivityTitle("nueva");
        setContentView(activityNuevaBinding.getRoot());


        etIdBuscar = findViewById(R.id.etIdBuscar);
        etId = findViewById(R.id.etId);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        btnIdBuscar = findViewById(R.id.btnIdBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnTodosBuscar = findViewById(R.id.btnTodosBuscar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setLayoutManager(new GridLayoutManager(this, 1));

        retrofit = new AdaptadorRetrofit().getAdaptador();
        api = retrofit.create(APIRest.class);

        getUsuarios(api);

        btnIdBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(nuevaActivity.this, "Inserta un ID para buscar", Toast.LENGTH_SHORT).show();
                } else {
                    getUsuario(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etIdBuscar.getText().toString().equals("")) {
                    Toast.makeText(nuevaActivity.this, "Inserta un ID para eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    eliminarUsuario(api, etIdBuscar.getText().toString());
                }
            }
        });

        btnTodosBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUsuarios(api);
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombres.getText().toString().equals("") || etApellidos.getText().toString().equals("")) {
                    Toast.makeText(nuevaActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    agregarUsuario(api, etNombres.getText().toString(), etApellidos.getText().toString());
                }
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etId.getText().toString().equals("") || etNombres.getText().toString().equals("") || etApellidos.getText().toString().equals("")) {
                    Toast.makeText(nuevaActivity.this, "Se deben de llenar los campos", Toast.LENGTH_SHORT).show();
                } else {
                    editarUsuario(api, etId.getText().toString(), etNombres.getText().toString(), etApellidos.getText().toString());
                }
            }
        });

    }

    public void getUsuario(final APIRest api, String idUsuario) {
        listaUsuarios.clear();
        Call<Usuario> call = api.ObtenerUusarios(idUsuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                switch (response.code()) {
                    case 200:
                        listaUsuarios.add(response.body());

                        etIdBuscar.setText("");

                        adaptadorUsuarios = new AdaptadorUsuarios(nuevaActivity.this, listaUsuarios);
                        rvUsuarios.setAdapter(adaptadorUsuarios);

                        break;
                    case 204:
                        Toast.makeText(nuevaActivity.this, "No existe ese registro", Toast.LENGTH_SHORT).show();

                        etIdBuscar.setText("");

                        getUsuarios(api);
                        break;

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    public void getUsuarios(APIRest api) {
        listaUsuarios.clear();
        Call<List<Usuario>> call = api.ObtenerUusarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                listaUsuarios = new ArrayList<Usuario>(response.body());

                adaptadorUsuarios = new AdaptadorUsuarios(nuevaActivity.this, listaUsuarios);
                rvUsuarios.setAdapter(adaptadorUsuarios);

            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {

            }
        });
    }

    public void eliminarUsuario(final APIRest api, String idUsuario) {
        listaUsuarios.clear();

        Call<Void> call = api.eliminarUsuario(idUsuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 200:
                        Toast.makeText(nuevaActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        getUsuarios(api);
                        break;
                    case 204:
                        Toast.makeText(nuevaActivity.this, "No se elimino el registro", Toast.LENGTH_SHORT).show();
                        etIdBuscar.setText("");
                        break;

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void agregarUsuario(final APIRest api, String nombres, String apellidos) {
        listaUsuarios.clear();
        Usuario usuario = new Usuario();
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);

        Call<Void> call = api.agregarUsuario(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(nuevaActivity.this, "Faltaron campos.", Toast.LENGTH_SHORT).show();
                        etNombres.setText("");
                        etApellidos.setText("");
                        break;
                    case 200:
                        Toast.makeText(nuevaActivity.this, "Se inserto correctamente", Toast.LENGTH_SHORT).show();
                        etNombres.setText("");
                        etApellidos.setText("");
                        getUsuarios(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void editarUsuario(final APIRest api, String idUsuario, String nombres, String apellidos) {
        listaUsuarios.clear();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombres(nombres);
        usuario.setApellidos(apellidos);

        Call<Void> call = api.editarUsuario(usuario);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                switch (response.code()) {
                    case 400:
                        Toast.makeText(nuevaActivity.this, "No se puede editar.", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etNombres.setText("");
                        etApellidos.setText("");
                        break;
                    case 200:
                        Toast.makeText(nuevaActivity.this, "Se edito correctamente", Toast.LENGTH_SHORT).show();
                        etId.setText("");
                        etNombres.setText("");
                        etApellidos.setText("");
                        getUsuarios(api);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    }
