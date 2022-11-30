package com.example.white_city;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIRest {
    //String URL_PHP = "v1/users/";

    @GET("v1/users/")
    Call<List<Usuario>> ObtenerUusarios();

    @GET("v1/users/")
    Call<Usuario> ObtenerUusarios(
            @Query("idUsuario") String idUusario
    );

    @POST("v1/users/")
    Call<Void> agregarUsuario(
            @Body Usuario usuario
        );

    @PUT("v1/users/")
    Call<Void> editarUsuario(
            @Body Usuario usuario
    );

    @DELETE("v1/users/")
    Call<Void> eliminarUsuario(
         @Query("idUsuario") String idUsuario
    );
}
