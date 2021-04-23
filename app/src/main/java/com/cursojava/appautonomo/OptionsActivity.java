package com.cursojava.appautonomo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends AppCompatActivity {

    private ImageView exit;
    private ImageView menuOptions;
    private Button optionsProfile;
    private Button tools;
    private Button configurations;
    private Button logout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exit = findViewById(R.id.exit_btnOptions);
        menuOptions = findViewById(R.id.imageMenuOptions);
        optionsProfile = findViewById(R.id.btn_OptionsProfile);
        tools = findViewById(R.id.btn_OptionsTools);
        configurations = findViewById(R.id.btn_OptionsConfig);
        logout = findViewById(R.id.btn_OptionsLogout);



        exit.setOnClickListener(v -> finish());
        // line to menuOptions
        // optionsProfile
        // tools
        // Configurations

        logout.setOnClickListener( v -> {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        });

    }
}
