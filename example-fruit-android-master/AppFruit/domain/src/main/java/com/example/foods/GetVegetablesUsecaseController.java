package com.example.foods;

import com.example.foods.entities.FruitsWrapper;
import com.example.foods.entities.VegetablesWrapper;
import com.example.foods.rest.RestFoodSource;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 9/16/15.
 */
public class GetVegetablesUsecaseController implements GetVegetablesUsecase {
    private final Bus mainBus;
    private final RestFoodSource restFoodSource;
    public GetVegetablesUsecaseController(Bus mainBus, RestFoodSource restFoodSource) {
        this.mainBus = mainBus;
        this.restFoodSource = restFoodSource;
        this.mainBus.register(this);

    }

    @Override
    public void execute() {
        requestVegetables();
    }

    @Override
    public void requestVegetables() {
        this.restFoodSource.getVegetables();
    }

    @Override
    public void sendVegetablesToPresenter(VegetablesWrapper response) {
        mainBus.post(response);
    }


    @Override
    public void unRegister() {
        mainBus.unregister(this);
    }
}
