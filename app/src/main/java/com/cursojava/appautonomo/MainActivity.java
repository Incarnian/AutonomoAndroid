package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.UserCall;
import com.cursojava.appautonomo.clients_management.ClientsActivity;
import com.cursojava.appautonomo.clients_management.CreateClientActivity;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.TokenBody;
import com.cursojava.appautonomo.model.UserResponse;
import com.cursojava.appautonomo.products_management.ProductsActivity;
import com.cursojava.appautonomo.suppliers_management.CreateSupplierActivity;
import com.cursojava.appautonomo.suppliers_management.SuppliersActivity;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LinearLayout productsLayout;
    private LinearLayout clientsLayout;
    private LinearLayout suppliersLayout;
    private LinearLayout optionsLayout;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    private TextView userName;
    private ProgressDialog progressDialog;

    private TokenBody tokenBody;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Puxar dados do usuario
         */
        tokenBody = (TokenBody) getIntent().getSerializableExtra(Constants.TOKEN_BODY);
        UserCall request = HttpClient.getInstance();
        Call<UserResponse> userLogged = request.getUserDetails( sp.getString(Constants.FULL_TOKEN, "") ,Long.parseLong(tokenBody.getSub()));

        userLogged.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful() && response.code() == 200) {
                    UserResponse user = response.body();
                    userName.setText( user.getName() );
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                finish();
            }
        });

        productsLayout = findViewById(R.id.layout_products);
        productsLayout.setOnClickListener(v -> goToProductsActivity());

        clientsLayout = findViewById(R.id.layout_clients);
        clientsLayout.setOnClickListener(v -> goToClientsActivity());

        suppliersLayout = findViewById(R.id.layout_suppliers);
        suppliersLayout.setOnClickListener(v -> goToSuppliersActivity());

        optionsLayout = findViewById(R.id.layout_options);
        optionsLayout.setOnClickListener(v -> goToOptionsActivity());

        userName = findViewById(R.id.user_name);
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