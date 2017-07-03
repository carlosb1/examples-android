package com.example.foods.mvp.presenters;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by carlos on 9/27/15.
 */
public class Utils {

    private static final String TAG = "AppFruits::Utils::";

    private static String[] months = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};


    public static String getMonth() {
        Calendar cal = Calendar.getInstance();
        String monthValue = new SimpleDateFormat("MM").format(cal.getTime());
        int indexMonth = Integer.parseInt(monthValue) - 1;
        return months[indexMonth];
    }

    public static  void PrintException (Exception e){
        if (e.getMessage() != null && e.getMessage().length()!=0) {
            Log.e(TAG, e.getMessage());
        } else {
            Log.e(TAG, "Error message was null");
        }

            /* Error to string  */
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        Log.e(TAG, sw.toString());
    }

}
