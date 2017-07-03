package com.example.suggestfruits;

import com.example.suggest_fruits.R;
import com.example.suggestfruits.exceptions.ExceptionHandler;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SuggestActivity extends Activity {
	
	private static final String TAG = "Suggest-fruits::SuggestActivity";
	private static CalendarFruit fruits;
	
    public static CalendarFruit getFruits() {
		return fruits;
	}


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_suggest);
		Loader loader = new Loader(this.getBaseContext());
		try {
			fruits = loader.load();
		} catch (Exception e) {
			ExceptionHandler.print(TAG, e, this.getBaseContext(), "It was impossible to load the configured image.");
		}
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.suggest, menu);
        return true;
    }

    
}
