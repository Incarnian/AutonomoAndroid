package com.cursojava.appautonomo.products_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend.products.ProductClient;
import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateProductActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productQuantity;
    private EditText productDescription;
    private EditText productPrice;
    private EditText productMeasurement;
    private EditText productSupplier;
    private Button btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        productName = findViewById(R.id.product_name);
        productQuantity = findViewById(R.id.product_quantity);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productMeasurement = findViewById(R.id.product_measurement);
        productSupplier = findViewById(R.id.product_supplier);

        btnCadastro = findViewById(R.id.btn_cadastrar);
        btnCadastro.setOnClickListener(v -> cadastrarProduto());
    }

    private void cadastrarProduto() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://500e16a19fd2.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductClient productClient = retrofit.create(ProductClient.class);


        ProductRequest produtoAserSalvo = new ProductRequest();

        produtoAserSalvo.setName(productName.getText().toString());
        produtoAserSalvo.setDescription(productDescription.getText().toString());
        produtoAserSalvo.setPrice(Double.parseDouble(productPrice.getText().toString()));
        produtoAserSalvo.setMeasurement(productMeasurement.getText().toString());
        produtoAserSalvo.setQuantity(Integer.parseInt(productQuantity.getText().toString()));
        produtoAserSalvo.setSupplierId(productSupplier.getText().toString());

        System.out.println("\n\n ============================== \n\n");
        System.out.println(produtoAserSalvo);
        System.out.println("\n\n ============================== \n\n");

        Call<ProductResponse> produtoCadastrado = productClient.saveProduct(produtoAserSalvo);

        produtoCadastrado.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 500) {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar produto", Toast.LENGTH_LONG).show();
                    } else if (response.code() == 201) {
                        Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Deu Ruim demais", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });

    }

}