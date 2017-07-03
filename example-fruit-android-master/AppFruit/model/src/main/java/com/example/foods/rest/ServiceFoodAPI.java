package com.example.foods.rest;

import com.example.foods.entities.VegetablesWrapper;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by carlos on 9/11/15.
 */
public interface ServiceFoodAPI {

    @GET("/food/api/v1.0/fruits")
    public void getFruits (Callback<com.example.foods.entities.FruitsWrapper> fruits);

    @GET("/food/api/v1.0/vegetables")
    public void getVegetables(Callback<VegetablesWrapper> vegetables);
}
