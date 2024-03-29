package com.example.metrovet_frontend.retrofit;

import com.example.metrovet_frontend.model.Dog;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;


public interface MetroVetAPI {

    // Add Dog
    @POST("/add-dog")
    Call <Dog> save(@Body Dog dog);

    // Get Dog by ID
    @GET("/dog/{id}")
    Call <List<Dog>> getDog();

    // Get all Dogs
    @GET("/dogs")
    Call<List<Dog>> getAllDogs();

    @DELETE("/dog/{id}")
    Call <Void> deleteDog(@Path("id") int id);

    @PUT("/update-dog/{id}")
    Call<Dog> updateDog(@Path("id") int id, @Body Dog dog);

    @GET("/dog/{id}")
    Call<Dog> getDog(@Path("id") int id);

}
