package com.example.foods.mvp.views;

import com.example.foods.entities.FruitsWrapper;
import com.example.foods.entities.VegetablesWrapper;

import java.util.List;

/**
 * Created by carlos on 9/19/15.
 */

public interface FoodDetailView extends MVPView {
    boolean isTheFruitListEmpty();

    void hideLoading();
    void showLoading();
    void showFruits(List<FruitsWrapper.Fruit> fruits);

    void hideActionLabel();

    void appendFruits(List<FruitsWrapper.Fruit> fruits);

    boolean isTheVegetablesListEmpty();

    void showVegetables(List<VegetablesWrapper.Vegetable> vegetables);

    void appendVegetables(List<VegetablesWrapper.Vegetable> vegetables);
}
