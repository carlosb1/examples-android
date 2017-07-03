package com.example.foods.di.modules;

import com.example.foods.GetFruitsUsecaseController;
import com.example.foods.GetVegetablesUsecase;
import com.example.foods.GetVegetablesUsecaseController;
import com.example.foods.rest.RestFoodSource;
import com.squareup.otto.Bus;
import com.example.foods.GetFruitsUsecase;

import dagger.Module;
import dagger.Provides;


@Module
public class FoodUsecasesModule {
    @Provides GetFruitsUsecase provideGetFruitsUsecase (Bus bus, RestFoodSource restFoodSource) {
        return new GetFruitsUsecaseController(bus, restFoodSource);
    }
    @Provides  GetVegetablesUsecase provideGetVegetablesUsecase (Bus bus, RestFoodSource restFoodSource) {
        return new GetVegetablesUsecaseController(bus, restFoodSource);
    }

}
