package com.example.foods;

import com.example.foods.entities.VegetablesWrapper;

/**
 * Created by carlos on 9/16/15.
 */
public interface GetVegetablesUsecase extends Usecase {
    /**
     * Request datasource the most popular movies
     */
    public void requestVegetables();

    public void sendVegetablesToPresenter (VegetablesWrapper response);

    public void unRegister ();

}
