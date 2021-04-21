package com.cursojava.appautonomo.products_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.adapters.ProductsAdapter;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.ProductCall;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView productsRecycleView;
    private List<ProductResponse> products;
    private ImageView addProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productsRecycleView = findViewById(R.id.products_list);

        addProductBtn = findViewById(R.id.add_product_btn);
        addProductBtn.setOnClickListener(v -> onAddNewProduct());


        ProductCall requests = HttpClient.getInstance();

        Call<List<ProductResponse>> productsResponse = requests.readProducts();

        productsResponse.enqueue(new Callback<List<ProductResponse>>() {

            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if(response.isSuccessful()) {
                    products = response.body();
                    ProductsAdapter productsAdapter = new ProductsAdapter(products);
                    productsRecycleView.setAdapter(productsAdapter);
                    productsRecycleView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                System.out.println("ERRRRRRRROOOOOOOOOOOOOOOOOOOOO");
            }

        });
    }

    private void onAddNewProduct() {
        Intent intent = new Intent(this, CreateProductActivity.class);
        startActivity(intent);
    }
}