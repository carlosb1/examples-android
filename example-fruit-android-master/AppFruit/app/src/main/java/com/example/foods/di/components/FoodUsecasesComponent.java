package com.example.foods.di.components;

import com.example.foods.di.modules.FoodUsecasesModule;
import com.example.foods.di.scopes.PerActivity;
import com.example.foods.views.activities.FoodActivity;

import dagger.Component;

/**
 * Created by carlos on 9/16/15.
 */
@PerActivity
@Component(dependencies = AppComponent.class, modules = FoodUsecasesModule.class)
public interface FoodUsecasesComponent {
    void inject (FoodActivity foodActivity);
}
