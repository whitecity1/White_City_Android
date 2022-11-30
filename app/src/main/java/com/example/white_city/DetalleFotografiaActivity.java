package com.example.white_city;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class DetalleFotografiaActivity extends DrawerBaseActivity {
    private ImageView imagen_detalle;
    private TextView nombre_detalle;
    private TextView descripcion_detalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_fotografia);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
    }

    private void initValues() {
        imagen_detalle= findViewById(R.id.imagen_detalle);
        nombre_detalle= findViewById(R.id.nombre_detalle);
        descripcion_detalle= findViewById(R.id.descripcion_detalle);

    }
    private void initViews() {
    }


}