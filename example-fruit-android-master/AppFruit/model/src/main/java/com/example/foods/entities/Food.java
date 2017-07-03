package com.example.foods.entities;

import java.util.Comparator;

/**
 * Created by carlos on 9/19/15.
 */
public abstract class Food {
    private boolean foodReady;
    protected String name_resource;

    public static class FoodComparator implements Comparator<Food> {
        @Override
        public int compare(Food food1, Food food2) {
            return food1.getName_resource().compareTo(food2.getName_resource());
        }
    }

    public void setFoodReady(boolean movieReady) {
        this.foodReady = foodReady;
    }

    public boolean isFoodReady() {
        return foodReady;
    }

    public String getName_resource() {
        return name_resource;
    }

    public void setName_resource(String name_resource) {
        this.name_resource = name_resource;
    }

}
