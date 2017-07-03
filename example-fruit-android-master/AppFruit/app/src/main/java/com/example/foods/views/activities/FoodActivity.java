package com.example.foods.views.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.example.foods.entities.Food;
import com.example.foods.entities.FruitsWrapper;
import com.example.foods.entities.VegetablesWrapper;
import com.example.foods.FoodApp;
import com.example.foods.R;
import com.example.foods.di.components.DaggerFoodUsecasesComponent;
import com.example.foods.di.modules.FoodUsecasesModule;
import com.example.foods.mvp.presenters.FoodPresenter;
import com.example.foods.mvp.views.FoodDetailView;
import com.example.foods.utils.RecyclerInsetsDecoration;
import com.example.foods.utils.RecyclerViewClickListener;
import com.example.foods.views.adapters.FoodAdapter;
import com.nispok.snackbar.SnackbarManager;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

/**
 * Created by carlos on 9/19/15.
 */
public class FoodActivity extends ActionBarActivity implements
        FoodDetailView, RecyclerViewClickListener, View.OnClickListener  {

    private final static String BUNDLE_FRUITS_WRAPPER       = "fruits_wrapper";
    private final static String BUNDLE_VEGETABLES_WRAPPER       = "vegetables_wrapper";
    private final static String BUNDLE_BACK_TRANSLATION     = "background_translation";
    public final static String EXTRA_FOOD_POSITION         = "food_position";
    public final static String EXTRA_FOOD_LOCATION         = "view_location";
    public final static String SHARED_ELEMENT_COVER         = "cover";
    private static final int PICK_FOOD_REQUEST = 1;


    public static SparseArray<Bitmap> sPhotoCache = new SparseArray<Bitmap>(1);

    @Optional
    @InjectView(R.id.activity_movies_background_view) View tabletBackground;
    @InjectView(R.id.activity_foods_toolbar)
    Toolbar toolbar;
    @InjectView(R.id.activity_foods_progress)
    ProgressBar progressBar;
    @InjectView(R.id.activity_foods_recycler)
    RecyclerView recycler;


    @Inject
    FoodPresenter foodPresenter;

    private FoodAdapter foodAdapter;
    public float backgroundTranslation;



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initializeDependencyInjector();
        initializeToolbar();
        initializeRecycler();
        initializeDrawer();

        if (savedInstanceState == null) {
            foodPresenter.attachView(this);

        } else {
            initializeFromParams(savedInstanceState);
        }
        foodPresenter.start();
    }

    public void onDestroy () {
        super.onDestroy();
        foodPresenter.stop();
    }


    private void initializeFromParams(Bundle savedInstanceState) {
        FruitsWrapper fruitsWrapper = (FruitsWrapper) savedInstanceState
                .getSerializable(BUNDLE_FRUITS_WRAPPER);

        VegetablesWrapper vegetablesWrapper = (VegetablesWrapper) savedInstanceState
                .getSerializable(BUNDLE_VEGETABLES_WRAPPER);


        foodPresenter.onlistOfFruitsReceived(fruitsWrapper);
        foodPresenter.onlistOfVegetablesReceived(vegetablesWrapper);



    }

    private void initializeRecycler() {

        recycler.addItemDecoration(new RecyclerInsetsDecoration(this));
        recycler.setOnScrollListener(recyclerScrollListener);
    }

    private void initializeDrawer() {

    }

    private void initializeToolbar() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        getSupportActionBar().setHomeAsUpIndicator(
                R.drawable.ic_menu_white_24dp);

        toolbar.setNavigationOnClickListener(this);
    }


    private void initializeDependencyInjector() {
        FoodApp app = (FoodApp) getApplication();
        DaggerFoodUsecasesComponent.builder().appComponent(app.getAppComponent()).foodUsecasesModule(new FoodUsecasesModule()).build().inject(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        if (foodAdapter != null) {

            outState.putSerializable(BUNDLE_FRUITS_WRAPPER, new FruitsWrapper(
                    foodAdapter.getFruits()));

            outState.putSerializable(BUNDLE_VEGETABLES_WRAPPER, new VegetablesWrapper(
                    foodAdapter.getVegetables()));


            outState.putFloat(BUNDLE_BACK_TRANSLATION, backgroundTranslation);
        }
    }


    private void openLink(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_FOOD_REQUEST) {
            foodPresenter.start();
        }
    }

    @Override
    public void onClick(View touchedView, int position, float x, float y) {
        Food food = foodAdapter.getFoods().get(position);

        if (food instanceof FruitsWrapper.Fruit) {
            openLink(((FruitsWrapper.Fruit)food).getLink());
        } else if (food instanceof VegetablesWrapper.Vegetable) {
            openLink(((VegetablesWrapper.Vegetable)food).getLink());

        }

    }


    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showLoading() {

        progressBar.setVisibility(View.VISIBLE);
    }



    @Override
    public void hideActionLabel() {
        SnackbarManager.dismiss();
    }

    @Override
    public void appendFruits(List<FruitsWrapper.Fruit> fruits) {
        foodAdapter.appendFruits(fruits);
    }

    @Override
    public void appendVegetables(List<VegetablesWrapper.Vegetable> vegetables) {
        foodAdapter.appendVegetables(vegetables);
    }


    @Override
    public boolean isTheVegetablesListEmpty() {
        return (foodAdapter == null) || foodAdapter.getVegetables().isEmpty();
    }
    @Override
    public boolean isTheFruitListEmpty() {
        return (foodAdapter == null) || foodAdapter.getFruits().isEmpty();
    }


    @Override
    public void showFruits(List<FruitsWrapper.Fruit> fruits) {
        if (foodAdapter==null) {
            foodAdapter = new FoodAdapter();
            foodAdapter.setRecyclerListListener(this);
            recycler.setAdapter(foodAdapter);
        }

        foodAdapter.addFruits(fruits);
    }
    @Override
    public void showVegetables(List<VegetablesWrapper.Vegetable> vegetables) {
        if (foodAdapter==null) {
            foodAdapter = new FoodAdapter();
            foodAdapter.setRecyclerListListener(this);
            recycler.setAdapter(foodAdapter);
        }
        foodAdapter.addVegetables(vegetables);

    }


    @Override
    public void hideLoading() {

        progressBar.setVisibility(View.GONE);
    }

    private void showToolbar() {

        toolbar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_off));
    }

    private void hideToolbar() {

        toolbar.startAnimation(AnimationUtils.loadAnimation(this,
                R.anim.translate_up_on));
    }

    private RecyclerView.OnScrollListener recyclerScrollListener = new RecyclerView.OnScrollListener() {
        public boolean flag;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount    = recycler.getLayoutManager().getChildCount();
            int totalItemCount      = recycler.getLayoutManager().getItemCount();
            int pastVisibleItems    = ((GridLayoutManager) recycler.getLayoutManager())
                    .findFirstVisibleItemPosition();

            if((visibleItemCount + pastVisibleItems) >= totalItemCount && !foodPresenter.isFruitsLoading() && !foodPresenter.isVegetablesLoading()) {
                foodPresenter.onEndListReached();
            }

            if (tabletBackground != null) {

                backgroundTranslation = tabletBackground.getY() - (dy / 2);
                tabletBackground.setTranslationY(backgroundTranslation);
            }

            // Is scrolling up
            if (dy > 10) {

                if (!flag) {

                    showToolbar();
                    flag = true;
                }

                // Is scrolling down
            } else if (dy < -10) {

                if (flag) {

                    hideToolbar();
                    flag = false;
                }
            }

        }
    };

    @Override
    public void onClick(View v) {

    }
}
