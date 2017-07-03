package com.example.foods.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foods.R;
import com.example.foods.utils.RecyclerViewClickListener;

/**
 * Created by carlos on 9/19/15.
 */
public class FoodViewHolder  extends RecyclerView.ViewHolder implements View.OnTouchListener{

    private final RecyclerViewClickListener onClickListener;
    TextView titleTextView;
    TextView authorTextView;
    ImageView coverImageView;

    public FoodViewHolder(View itemView, RecyclerViewClickListener onClickListener) {

        super(itemView);

        titleTextView = (TextView) itemView.findViewById(R.id.item_food_name);
        coverImageView = (ImageView) itemView.findViewById(R.id.item_movie_cover);
        coverImageView.setDrawingCacheEnabled(true);
        coverImageView.setOnTouchListener(this);
        this.onClickListener = onClickListener;
    }

    public void setReady(boolean ready) {

        coverImageView.setTag(ready);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

            onClickListener.onClick(v, getPosition(), event.getX(), event.getY());
        }
        return true;
    }
}
