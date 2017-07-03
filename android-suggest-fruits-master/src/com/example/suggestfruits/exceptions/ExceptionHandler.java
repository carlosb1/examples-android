package com.example.suggestfruits.exceptions;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class ExceptionHandler {

	public synchronized static void print(final String TAG, Exception exception, String message) {
		exception.printStackTrace();
		Log.e(TAG, message);
		Log.e(TAG, exception.getLocalizedMessage());
	}

	public synchronized static void print(final String TAG, Exception exception, Context context, String message) {
		print(TAG, exception, message);
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}
}
