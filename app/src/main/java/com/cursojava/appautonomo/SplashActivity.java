package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private boolean firstLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferencesUtil.initialize(getApplicationContext());
        sp = SharedPreferencesUtil.getSharedPreferences();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        System.out.println("\n\n================= ENTREI NA SPLASH ===============\n\n");

        firstLogin = sp.getBoolean(Constants.FIRST_LOGIN, true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(firstLogin) {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}