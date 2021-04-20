package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cursojava.appautonomo.clients_management.CreateClientActivity;
import com.cursojava.appautonomo.products_management.ProductsActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout productsLayout;
    private LinearLayout clientsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsLayout = findViewById(R.id.layout_products);
        productsLayout.setOnClickListener(v -> goToProductsActivity());

        clientsLayout = findViewById(R.id.layout_clients);
        clientsLayout.setOnClickListener(v -> goToClientsActivity());
    }

    private void goToProductsActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
    private void goToClientsActivity() {
        Intent intent = new Intent(this, CreateClientActivity.class);
        startActivity(intent);
    }
}