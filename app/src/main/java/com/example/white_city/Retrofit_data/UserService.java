package com.example.white_city.Retrofit_data;

import com.example.white_city.Modelo.RegisterRequest;
import com.example.white_city.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("v1/users")
    Call<RegisterResponse> registerUsers(@Body RegisterRequest registerRequest);
}
