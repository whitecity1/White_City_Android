package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.white_city.Modelo.RegisterRequest;
import com.example.white_city.Retrofit_data.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText nombres, apellidos, email, password, password_confirmation;
   Button buttonRegister;


    TextView iralacuenta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombres = findViewById(R.id.Nombres);
       // apellidos = findViewById(R.id.Apellidos);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        password_confirmation = findViewById(R.id.VerificarContraseña);


        iralacuenta=findViewById(R.id.iralacuenta);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(nombres.getText().toString())  || TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(password_confirmation.getText().toString()) ) {

                    String message = "Todos los campos son requeridos";
                    Toast.makeText(RegisterActivity.this,  message, Toast.LENGTH_SHORT).show();
                } else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setNombres(nombres.getText().toString());
                    //registerRequest.setApellidos(apellidos.getText().toString());
                    registerRequest.setEmail(email.getText().toString());
                    registerRequest.setPasssword(password.getText().toString());
                    registerRequest.setPasssword_confirmation(password_confirmation.getText().toString());
                    registerUser(registerRequest);
                }

            }
        });
    }

    public void registerUser(RegisterRequest registerRequest) {

        Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {

                    String message = "succesfull";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                    finish();
                } else {
                    String message = "Ha ocurrido un error";
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }
                }


            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                String message = t.getLocalizedMessage();
                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    }


