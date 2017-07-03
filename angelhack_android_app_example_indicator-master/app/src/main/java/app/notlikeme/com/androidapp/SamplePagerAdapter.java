package app.notlikeme.com.androidapp;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class SamplePagerAdapter extends PagerAdapter {

    private final Random random = new Random();
    private int mSize;

    public SamplePagerAdapter() {
        mSize = 3;
    }

    public SamplePagerAdapter(int count) {
        mSize = count;
    }

    @Override
    public int getCount() {
        return mSize;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View layout = null;
        if (position == 1) {
            LayoutInflater inflater = LayoutInflater.from(collection.getContext());
            layout =  inflater.inflate(R.layout.activity_hobbies,null);
            collection.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        } else if (position == 2) {
            LayoutInflater inflater = LayoutInflater.from(collection.getContext());
            layout =  inflater.inflate(R.layout.activity_photo,null);
            collection.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        } else{
            LayoutInflater inflater = LayoutInflater.from(collection.getContext());
            layout =  inflater.inflate(R.layout.activity_main,null);
            collection.addView(layout, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
    }


        return layout;
    }

    public void addItem() {
        mSize++;
        notifyDataSetChanged();
    }

    public void removeItem() {
        mSize--;
        mSize = mSize < 0 ? 0 : mSize;

        notifyDataSetChanged();
    }
}