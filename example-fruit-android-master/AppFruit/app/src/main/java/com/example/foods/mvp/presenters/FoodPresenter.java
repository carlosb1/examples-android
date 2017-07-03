package com.example.foods.mvp.presenters;

import com.example.foods.GetFruitsUsecase;
import com.example.foods.GetVegetablesUsecase;
import com.example.foods.entities.FruitsWrapper;
import com.example.foods.entities.VegetablesWrapper;
import com.example.foods.mvp.views.FoodDetailView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by carlos on 9/19/15.
 */
public class FoodPresenter  extends Presenter {
    private final Bus bus;
    private FoodDetailView foodView;
    private final GetFruitsUsecase fruitsUsecase;
    private final GetVegetablesUsecase vegetablesUsecase;
    private boolean isFruitsLoading = false;
    private boolean isVegetablesLoading = false;

    @Inject
    public FoodPresenter(GetVegetablesUsecase vegetablesUsecase, GetFruitsUsecase fruitsUsecase,  Bus bus) {
        this.bus = bus;
        this.fruitsUsecase = fruitsUsecase;
        this.vegetablesUsecase = vegetablesUsecase;

    }

    public void attachView (FoodDetailView foodView) {

        this.foodView = foodView;
    }


    @Override
    public void start() {

        if (foodView.isTheFruitListEmpty() || foodView.isTheVegetablesListEmpty()) {
            bus.register(this);
            this.foodView.showLoading();
            this.vegetablesUsecase.execute();
            this.fruitsUsecase.execute();
        }
    }


    @Override
    public void stop() {
        try {
            bus.unregister(this);
        }catch (Exception e) {
            Utils.PrintException(e);
        }
    }

    @Subscribe
    public void onlistOfFruitsReceived(FruitsWrapper fruitsWrapper) {
        foodView.hideLoading();
        if (foodView.isTheFruitListEmpty()) {
            filterMonthFruits(fruitsWrapper);
            foodView.showFruits(fruitsWrapper.getFruits());
        }
        isFruitsLoading = false;
    }

    private void filterMonthFruits(FruitsWrapper response) {
        String month = Utils.getMonth();
        List<FruitsWrapper.Fruit> fruits = response.getFruits();

        List<FruitsWrapper.Fruit> newFruits = new ArrayList<FruitsWrapper.Fruit>();
        for (FruitsWrapper.Fruit fruit: fruits) {
            if (fruit.getMonths().contains(month)) {
                newFruits.add(fruit);
            }
        }
        response.getFruits().clear();
        response.getFruits().addAll(newFruits);

    }



    @Subscribe
    public void onlistOfVegetablesReceived(VegetablesWrapper vegetablesWrapper) {
        foodView.hideLoading();
        if (foodView.isTheVegetablesListEmpty()) {
            filterMonthVegetables(vegetablesWrapper);
            foodView.showVegetables(vegetablesWrapper.getVegetables());
        }

        isVegetablesLoading = false;


    }


    private void filterMonthVegetables(VegetablesWrapper response) {
        String month = Utils.getMonth();
        List<VegetablesWrapper.Vegetable> vegetables = response.getVegetables();

        List<VegetablesWrapper.Vegetable> newVegetables = new ArrayList<VegetablesWrapper.Vegetable>();
        for (VegetablesWrapper.Vegetable vegetable: vegetables) {
            if (vegetable.getMonths().contains(month)) {
                newVegetables.add(vegetable);
            }
        }
        response.getVegetables().clear();
        response.getVegetables().addAll(newVegetables);

    }

    public boolean isFruitsLoading() {
        return isFruitsLoading;
    }

    public void setIsFruitsLoading(boolean isFruitsLoading) {
        this.isFruitsLoading = isFruitsLoading;
    }

    public boolean isVegetablesLoading() {
        return isVegetablesLoading;
    }

    public void setIsVegetablesLoading(boolean isVegetablesLoading) {
        this.isVegetablesLoading = isVegetablesLoading;
    }



    public void onEndListReached () {

        fruitsUsecase.execute();
        vegetablesUsecase.execute();
        this.isFruitsLoading = true;
        this.isVegetablesLoading = true;

    }



}
