package com.cursojava.appautonomo.clients_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cursojava.appautonomo.R;

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
        Toast.makeText(this, "Clicou no botão criar cliente", Toast.LENGTH_LONG).show();
    }
}