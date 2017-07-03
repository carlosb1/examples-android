package com.example.suggestfruits;

import java.util.Calendar;

import com.example.suggest_fruits.R;
import com.example.suggestfruits.exceptions.ExceptionHandler;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class SuggestWidget extends AppWidgetProvider {
	private static final String TAG = "Suggest-fruits::SuggestWidget";
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// There may be multiple widgets active, so update all of them
		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
		}
	}

	@Override
	public void onEnabled(Context context) {

	}

	@Override
	public void onDisabled(Context context) {
		// Enter relevant functionality for when the last widget is disabled
	}

	private void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId) {
		
	   CharSequence widgetText = getFruits(Calendar.getInstance().get(Calendar.MONTH));
		
		// Construct the RemoteViews object
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.suggest_widget);
		views.setTextViewText(R.id.appwidget_text, widgetText);

		// Instruct the widget manager to update the widget
		appWidgetManager.updateAppWidget(appWidgetId, views);
	}

	private CharSequence getFruits(int month) {
		String [] fruits = SuggestActivity.getFruits().getFruits().get(month);			
		String fruitsToConcatenate = "";
		for (String fruit: fruits)  {
			fruitsToConcatenate+=" "+fruit+", ";
		}
		return fruitsToConcatenate;
	}
	
}
