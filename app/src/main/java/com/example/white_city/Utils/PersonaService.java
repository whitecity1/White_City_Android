package com.example.white_city.Utils;

import com.example.white_city.Model.Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonaService {

    @GET("v1/users/")
    Call<List<Persona>> getPersonas();
}
