package com.example.white_city.Retrofit_data;


import com.example.white_city.Model.Persona;
import com.example.white_city.Modelo.Evento;
import com.example.white_city.Modelo.Fotografias;
import com.example.white_city.Modelo.Lugarturistico;
import com.example.white_city.Modelo.Recomendado;
import com.example.white_city.Modelo.Restaurante;
import com.example.white_city.Modelo.Rutaturistica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApiService {
    @GET("v1/fotografias/")
    Call<List<Fotografias>> getFotografias();

    @POST("v1/fotografias/")
    Call<Fotografias> addFotografia(@Body Fotografias fotografias);

    @PUT("v1/fotografias/{id}")
    Call<Fotografias> updateFotografia(@Body Fotografias fotografias, @Path("id") int id);

    @DELETE("v1/fotografias/{id}")
    Call<Fotografias> deleteFotografia(@Path("id") int id);

    @GET("v1/lugaresturisticos")
    Call<List<Lugarturistico>> getLugarturistico();

    @GET("v1/recomendados/")
    Call<List<Recomendado>> getRecomendado();

    @GET("v1/eventos")
    Call<List<Evento>> getEventos();

    @GET("v1/restaurantes")
    Call<List<Restaurante>> getRestaurantes();

    @GET("v1/rutasturisticas")
    Call<List<Rutaturistica>> getRutasturisticas();

    @GET("v1/users")
    Call<List<Persona>> getPersona();
}






