package com.cursojava.appautonomo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferences mSharedePreferences;

    private SharedPreferencesUtil() {
    }

    public static void initialize(Context context) {
        if( mSharedePreferences == null) {
            SharedPreferencesUtil.mSharedePreferences = context
                            .getApplicationContext()
                            .getSharedPreferences("AutonomoSharedPreferences", Context.MODE_PRIVATE);
        }
    }

    public static SharedPreferences getSharedPreferences() {
        return mSharedePreferences;
    }
}
