package com.example.foods;

import com.example.foods.entities.FruitsWrapper;

/**
 * Created by carlos on 9/16/15.
 */
public interface GetFruitsUsecase extends Usecase {
    /**
     * Request datasource the most popular movies
     */
    public void requestFruits();

    public void sendFruitsToPresenter (FruitsWrapper response);

    public void unRegister ();

}
