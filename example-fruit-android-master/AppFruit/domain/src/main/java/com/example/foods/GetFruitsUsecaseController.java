package com.example.foods;

import com.example.foods.entities.FruitsWrapper;
import com.example.foods.rest.RestFoodSource;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by carlos on 9/16/15.
 */
public class GetFruitsUsecaseController implements GetFruitsUsecase {

    private final Bus mainBus;
    private final RestFoodSource restFoodSource;

    @Inject
    public GetFruitsUsecaseController(Bus mainBus, RestFoodSource restFoodSource) {
        this.mainBus = mainBus;
        this.restFoodSource = restFoodSource;
        this.mainBus.register(this);
    }


    @Override
    public void requestFruits() {
        this.restFoodSource.getFruits();

    }

    @Override
    public void sendFruitsToPresenter (FruitsWrapper response) {
        mainBus.post(response);


    }



    @Override
    public void unRegister() {
        mainBus.unregister(this);
    }

    @Override
    public void execute() {
        requestFruits();
    }
}
