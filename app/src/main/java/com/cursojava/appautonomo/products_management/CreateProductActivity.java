package com.cursojava.appautonomo.products_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.ProductCall;
import com.cursojava.appautonomo.backend_request.SupplierCall;
import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.SupplierResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProductActivity extends AppCompatActivity {

    private TextView title;
    private EditText productName, productQuantity, productDescription, productPrice, productMeasurement ;
    private Spinner spinnerSupplier;
    private Button btnCadastro;
    private ImageView btnExit;
    private ArrayAdapter<SupplierResponse> suppliers;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        title = findViewById(R.id.title);
        productName = findViewById(R.id.product_name);
        productQuantity = findViewById(R.id.product_quantity);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productMeasurement = findViewById(R.id.product_measurement);
        spinnerSupplier = findViewById(R.id.product_supplier);

        btnExit = findViewById(R.id.exit_btn);
        btnExit.setOnClickListener(v -> finish());

        btnCadastro = findViewById(R.id.btn_cadastrar);

        ProductResponse productResponse = (ProductResponse) getIntent().getSerializableExtra(Constants.PRODUCT_UPDATE);
        btnCadastro.setOnClickListener(v -> newProduct(productResponse));
        if(productResponse != null) {
            // Edit

            title.setText("Atualizar produto");

            btnCadastro.setText("Atualizar Produto");
            productName.setText( productResponse.getName() );
            productQuantity.setText(String.valueOf(productResponse.getQuantity()));
            productDescription.setText(productResponse.getDescription());
            productPrice.setText(String.valueOf(productResponse.getPrice()));
            productMeasurement.setText(productResponse.getMeasurement());
        }
        else {
            //Create

            title.setText("Novo produto");

            btnCadastro.setText("Cadastrar Produto");
        }
    }

    private void newProduct(ProductResponse productResponse) {

        ProductCall productCall = HttpClient.getInstance();
        SupplierResponse supplier = (SupplierResponse) spinnerSupplier.getSelectedItem();
        ProductRequest produtoAserSalvo =
                new ProductRequest.Builder()
                        .name(productName.getText().toString())
                        .description(productDescription.getText().toString())
                        .price(Double.parseDouble(productPrice.getText().toString()))
                        .measurement(productMeasurement.getText().toString())
                        .quantity(Integer.parseInt(productQuantity.getText().toString()))
                        .supplierId(supplier.getId())
                        .build();

        if(productResponse != null) {
            // Edit
            Call<Void> request;
            String token = sp.getString(Constants.FULL_TOKEN, "");

            request = productCall
                    .editProduct(token, sp.getLong(Constants.USER_ID, 0), productResponse.getId(), produtoAserSalvo);

            request.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        if (response.code() == 200) {
                            Toast.makeText(getApplicationContext(), "Produto atualizado com sucesso", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                    else {
                        if (response.code() == 400) {
                            Toast.makeText(getApplicationContext(), "Dados invalidos", Toast.LENGTH_LONG).show();
                        }
                        else if (response.code() == 500) {
                            Toast.makeText(getApplicationContext(), "Erro de servidor", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erro ao falar com o servidor", Toast.LENGTH_LONG).show();
                }
            });
        }

        else {
            // Create
            Call<ProductResponse> request;
            String token = sp.getString(Constants.FULL_TOKEN, "");
            request = productCall
                    .createProduct(token, sp.getLong(Constants.USER_ID, 0) ,produtoAserSalvo);

            request.enqueue(new Callback<ProductResponse>() {
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


    @Override
    protected void onStart() {

        // Suppliers Request
        SupplierCall supplierCall = HttpClient.getInstance();
        String token = sp.getString(Constants.FULL_TOKEN, "");

        Call<List<SupplierResponse>> allSuppliers = supplierCall.getSuppliers(token, sp.getLong(Constants.USER_ID, 0));

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


    }

}