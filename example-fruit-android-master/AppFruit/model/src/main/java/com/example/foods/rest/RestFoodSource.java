package com.example.foods.rest;

import com.example.foods.entities.VegetablesWrapper;
import com.example.foods.common.utils.Constants;
import com.squareup.otto.Bus;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by carlos on 9/11/15.
 */
public class RestFoodSource implements RestDataFoodSource {

    private final ServiceFoodAPI foodDBAPI;
    private final Bus bus;

    public RestFoodSource(Bus bus) {

        RestAdapter foodAPIRest = new RestAdapter.Builder()
                .setEndpoint(Constants.FOOD_DB_HOST)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        foodDBAPI = foodAPIRest.create(ServiceFoodAPI.class);
        this.bus = bus;
    }


    public void getFruits () {
            foodDBAPI.getFruits(retrofitCallback);

    }

    public void getVegetables () {
        foodDBAPI.getVegetables(retrofitCallback);

    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {
            if (o instanceof com.example.foods.entities.FruitsWrapper) {
                com.example.foods.entities.FruitsWrapper fruits = (com.example.foods.entities.FruitsWrapper) o;
                bus.post(fruits);
            } else if (o instanceof VegetablesWrapper) {
                VegetablesWrapper vegetables = (VegetablesWrapper) o;
                bus.post(vegetables);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            System.out.printf("[DEBUG] RestFoodSource failure - " + error.getMessage());
        }

    };


}
