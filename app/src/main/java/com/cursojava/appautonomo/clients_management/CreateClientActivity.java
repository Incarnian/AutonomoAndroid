package com.cursojava.appautonomo.clients_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend.products.ProductClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateClientActivity extends AppCompatActivity {

    private ImageView exitBtn;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        exitBtn = findViewById(R.id.exit_btn);
        exitBtn.setOnClickListener(v -> finish());

        registerBtn = findViewById(R.id.btn_register);
        registerBtn.setOnClickListener(v -> saveClient());
    }

    private void saveClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://e39b06d47541.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductClient productClient = retrofit.create(ProductClient.class);
    }
}