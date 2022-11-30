package com.example.white_city;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;




import com.example.white_city.databinding.ActivityEventosBinding;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MainActivity extends DrawerBaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ActivityEventosBinding activityEventosBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(2.4415276414488187, -76.6061915195794);
        LatLng sydney2 = new LatLng(2.4408026292325964, -76.61235972100246);
        LatLng sydney3 = new LatLng(2.451677771349829, -76.60824758672042);

        mMap.addMarker(new MarkerOptions().position(sydney2).title("Parque Benito Juarez"));
                mMap.addMarker(new MarkerOptions().position(sydney3).title("Terminal de Transporte"));
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Parque Caldas"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom (sydney,0));
    }
}

