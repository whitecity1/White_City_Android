package com.example.white_city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void OnButtonSingUpClicked(View view){
        Intent intent =new Intent(HomeActivity.this,RegisterActivity.class);
        startActivity(intent);

    }
    public void OnButtonSingInClicked(View view){
        Intent intent =new Intent(HomeActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}