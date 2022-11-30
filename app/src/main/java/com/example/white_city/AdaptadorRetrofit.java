package com.example.white_city;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorRetrofit {

    Retrofit retrofit;

    public AdaptadorRetrofit() {

    }

    public  Retrofit getAdaptador() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/users")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
