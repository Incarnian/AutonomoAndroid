package com.cursojava.appautonomo.products_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.adapters.ProductsAdapter;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.ProductCall;
import com.cursojava.appautonomo.utils.RecyclerItemClickListener;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView productsRecycleView;
    private List<ProductResponse> products;
    private ImageView addProductBtn;
    private ProgressDialog progressDialog;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        productsRecycleView = findViewById(R.id.products_list);

        //Evento de clique
        productsRecycleView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        productsRecycleView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Edição

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Deleção
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        addProductBtn = findViewById(R.id.add_product_btn);
        addProductBtn.setOnClickListener(v -> onAddNewProduct());

        //Criando o progress dialog quando entra na tela
        progressDialog = new ProgressDialog(ProductsActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        getProducts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getProducts();
    }

    private void getProducts() {
        ProductCall requests = HttpClient.getInstance();

        Call<List<ProductResponse>> productsResponse = requests.readProducts(sp.getLong(Constants.USER_ID, 0));

        productsResponse.enqueue(new Callback<List<ProductResponse>>() {

            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if(response.isSuccessful()) {
                    products = response.body();
                    ProductsAdapter productsAdapter = new ProductsAdapter(products);
                    productsRecycleView.setAdapter(productsAdapter);
                    productsRecycleView.setHasFixedSize(true);
                    progressDialog.dismiss();
                }
                else{
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(),"Não foi possível receber as informações do banco de dados", Toast.LENGTH_SHORT).show();
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