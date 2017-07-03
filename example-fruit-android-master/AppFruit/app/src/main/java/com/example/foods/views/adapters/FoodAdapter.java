package com.example.foods.views.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foods.entities.Food;
import com.example.foods.entities.FruitsWrapper;
import com.example.foods.entities.VegetablesWrapper;

import com.example.foods.R;
import com.example.foods.utils.RecyclerViewClickListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by carlos on 9/19/15.
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodViewHolder> {
    private Context context;
    private List<Food> foods;
    private RecyclerViewClickListener recyclerClickListener;
    private List<FruitsWrapper.Fruit> fruits;
    private List<VegetablesWrapper.Vegetable> vegetables;




    public FoodAdapter (List<FruitsWrapper.Fruit> fruits, List<VegetablesWrapper.Vegetable> vegetables) {
        this.foods = new ArrayList<Food>();
        this.foods.addAll(fruits);
        this.foods.addAll(vegetables);
        this.fruits = fruits;
        this.vegetables = vegetables;
        Collections.sort(this.foods,new Food.FoodComparator());

    }

    public FoodAdapter () {
        this.foods = new ArrayList<Food>();
        this.fruits = new ArrayList<FruitsWrapper.Fruit>();
        this.vegetables = new ArrayList<VegetablesWrapper.Vegetable>();

    }

    public void setRecyclerListListener(RecyclerViewClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }


    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View rowView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_food, viewGroup, false);

        this.context = viewGroup.getContext();

        return new FoodViewHolder(rowView, recyclerClickListener);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {

        Food selectedFood = foods.get(position);

        /*FIXME  ugly method (avoid instanceof) to do get info*/
        String title;
        String posterURL;
        if (selectedFood instanceof FruitsWrapper.Fruit) {
            FruitsWrapper.Fruit fruit = (FruitsWrapper.Fruit) selectedFood;
            //TODO change url...
            title = fruit.getName();
            posterURL = fruit.getImagePath();

        } else if (selectedFood instanceof VegetablesWrapper.Vegetable) {
            VegetablesWrapper.Vegetable vegetable = (VegetablesWrapper.Vegetable) selectedFood;
            title = vegetable.getName();
            posterURL = vegetable.getImagePath();
        } else {
            title = "Unknown";
            posterURL = "Unknown";

        }


        holder.titleTextView.setText(title);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            holder.coverImageView.setTransitionName("cover" + position);


        Picasso.with(context)
                .load(posterURL)
                .fit().centerCrop()
                .into(holder.coverImageView, new Callback() {
                    @Override
                    public void onSuccess() {

                        foods.get(position).setFoodReady(true);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

   @Override
    public int getItemCount() {
        return foods.size();
    }


    public boolean isMovieReady(int position) {

        return foods.get(position).isFoodReady();
    }

    public void appendFood(List<Food> foodList) {

        foods.addAll(foodList);
        notifyDataSetChanged();
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void addFruits ( List<FruitsWrapper.Fruit> addedFruits) {
        this.fruits.addAll(addedFruits);
        this.foods.addAll(addedFruits);
        Collections.sort(this.foods, new Food.FoodComparator());
    }
    public void addVegetables ( List<VegetablesWrapper.Vegetable> addedVegetables) {
        this.vegetables.addAll(addedVegetables);
        this.foods.addAll(addedVegetables);
        Collections.sort(this.foods, new Food.FoodComparator());
    }


    public List<FruitsWrapper.Fruit> getFruits() {
        return fruits;
    }

    public List<VegetablesWrapper.Vegetable> getVegetables() {
        return vegetables;
    }

    public void appendVegetables(List<VegetablesWrapper.Vegetable> vegetables) {
        this.vegetables = vegetables;
    }
    public void appendFruits(List<FruitsWrapper.Fruit> fruits) {
        this.fruits = fruits;
    }



}
