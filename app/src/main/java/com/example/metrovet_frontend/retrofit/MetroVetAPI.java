package com.example.metrovet_frontend.retrofit;

import com.example.metrovet_frontend.model.Dog;

import retrofit2.Call;
import retrofit2.http.*;


public interface MetroVetAPI {

    @POST("/add-dog")
    Call <Dog> save(@Body Dog dog);


}
