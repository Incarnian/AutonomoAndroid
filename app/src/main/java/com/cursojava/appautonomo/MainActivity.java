package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cursojava.appautonomo.clients_management.ClientsActivity;
import com.cursojava.appautonomo.clients_management.CreateClientActivity;
import com.cursojava.appautonomo.model.UserResponse;
import com.cursojava.appautonomo.products_management.ProductsActivity;
import com.cursojava.appautonomo.suppliers_management.CreateSupplierActivity;
import com.cursojava.appautonomo.suppliers_management.SuppliersActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout productsLayout;
    private LinearLayout clientsLayout;
    private LinearLayout suppliersLayout;
    private LinearLayout optionsLayout;

    private TextView userName;
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

        optionsLayout = findViewById(R.id.layout_options);
        optionsLayout.setOnClickListener(v -> goToOptionsActivity());

        userName = findViewById(R.id.user_name);

        Intent intent = getIntent();
        UserResponse user = (UserResponse) intent.getSerializableExtra("user");

        if(user != null) {
            userName.setText(user.getName());
        }

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
        Intent intent = new Intent(this, SuppliersActivity.class);
        startActivity(intent);
    }

    private void goToOptionsActivity() {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }
}