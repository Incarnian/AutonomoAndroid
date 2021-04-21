package com.cursojava.appautonomo.products_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.ProductCall;
import com.cursojava.appautonomo.backend_request.SupplierCall;
import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.SupplierResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProductActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productQuantity;
    private EditText productDescription;
    private EditText productPrice;
    private EditText productMeasurement;
    private Spinner spinnerSupplier;
    private Button btnCadastro;
    private ImageView btnExit;
    private ArrayAdapter<SupplierResponse> suppliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        productName = findViewById(R.id.product_name);
        productQuantity = findViewById(R.id.product_quantity);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productMeasurement = findViewById(R.id.product_measurement);
        spinnerSupplier = findViewById(R.id.product_supplier);

        btnExit = findViewById(R.id.exit_btn);
        btnExit.setOnClickListener(v -> finish());

        btnCadastro = findViewById(R.id.btn_cadastrar);
        btnCadastro.setOnClickListener(v -> cadastrarProduto());
    }

    @Override
    protected void onStart() {

        // Suppliers Request
        SupplierCall supplierCall = HttpClient.getInstance();

        Call<List<SupplierResponse>> allSuppliers = supplierCall.getSuppliers();

        super.onStart();
        allSuppliers.enqueue(new Callback<List<SupplierResponse>>() {
            @Override
            public void onResponse(Call<List<SupplierResponse>> call, Response<List<SupplierResponse>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        suppliers = new ArrayAdapter<SupplierResponse>(getApplicationContext(), android.R.layout.simple_spinner_item, response.body());
                        suppliers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerSupplier.setAdapter(suppliers);

                        spinnerSupplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                SupplierResponse supplierSelected = (SupplierResponse) parent.getSelectedItem();
                                displaySelectedSupplier(supplierSelected);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro servidor", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierResponse>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro de rede", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void displaySelectedSupplier(SupplierResponse supplier) {
        String name = supplier.getName();
        Long id = supplier.getId();

        String data = "Nome: " + name + "\nID: " + id;

        Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
    }

    private void cadastrarProduto() {

        // Products Request
        ProductCall productCall = HttpClient.getInstance();

        SupplierResponse supplier = (SupplierResponse) spinnerSupplier.getSelectedItem();
        ProductRequest produtoAserSalvo =
                new ProductRequest.Builder()
                        .setName(productName.getText().toString())
                        .setDescription(productDescription.getText().toString())
                        .setPrice(Double.parseDouble(productPrice.getText().toString()))
                        .setMeasurement(productMeasurement.getText().toString())
                        .setQuantity(Integer.parseInt(productQuantity.getText().toString()))
                        .setSupplierId(supplier.getId())
                        .build();

        Call<ProductResponse> produtoCadastrado = productCall.createProduct(produtoAserSalvo);

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