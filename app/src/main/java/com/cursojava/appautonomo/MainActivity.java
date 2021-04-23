package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cursojava.appautonomo.clients_management.ClientsActivity;
import com.cursojava.appautonomo.clients_management.CreateClientActivity;
import com.cursojava.appautonomo.products_management.ProductsActivity;
import com.cursojava.appautonomo.suppliers_management.CreateSupplierActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout productsLayout;
    private LinearLayout clientsLayout;
    private LinearLayout suppliersLayout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsLayout = findViewById(R.id.layout_products);
        productsLayout.setOnClickListener(v -> goToProductsActivity());

        clientsLayout = findViewById(R.id.layout_clients);
        clientsLayout.setOnClickListener(v -> goToClientsActivity());

        suppliersLayout = findViewById(R.id.layout_suppliers);
        suppliersLayout.setOnClickListener(v -> goToSuppliersActivity());
    }

    private void goToProductsActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);


    }
    private void goToClientsActivity() {
        Intent intent = new Intent(this, ClientsActivity.class);
        startActivity(intent);
    }
    //Alterar pra ir pra lista de suppliers
    private void goToSuppliersActivity() {
        Intent intent = new Intent(this, CreateSupplierActivity.class);
        startActivity(intent);
    }
}