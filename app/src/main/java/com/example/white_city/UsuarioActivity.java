package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.white_city.databinding.ActivityRutasturisticasBinding;
import com.example.white_city.databinding.ActivityUsuarioBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsuarioActivity extends DrawerBaseActivity {

   // ActivityUsuarioBinding activityUsuarioBinding;
    ActivityUsuarioBinding activityUsuarioBinding;

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
        activityUsuarioBinding = ActivityUsuarioBinding.inflate(getLayoutInflater());
        allocateActivityTitle("Usuario");
        setContentView(activityUsuarioBinding.getRoot());

    }
}