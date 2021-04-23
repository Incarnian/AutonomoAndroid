package com.cursojava.appautonomo.suppliers_management;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.SupplierCall;
import com.cursojava.appautonomo.model.Address;
import com.cursojava.appautonomo.model.Phone;
import com.cursojava.appautonomo.model.SupplierRequest;
import com.cursojava.appautonomo.model.SupplierResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSupplierActivity extends AppCompatActivity {

    private EditText supplierName;
    private EditText supplierCNPJ;
    private EditText supplierEmail;
    private EditText supplierPhone;
    private EditText supplierAddressStreet;
    private EditText supplierAddressNeighborhood;
    private EditText supplierAddressNumber;
    private EditText supplierAddressCity;
    private EditText supplierAddressCep;
    private Button createSupplier;
    private ImageView exitBtn;
    //private EditText supplierUF;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_supplier);

        supplierName = findViewById(R.id.supplier_name);
        supplierCNPJ = findViewById(R.id.supplier_cnpj);
        supplierEmail = findViewById(R.id.supplier_email);
        supplierPhone = findViewById(R.id.supplier_phone);
        supplierAddressStreet = findViewById(R.id.supplier_address_street);
        supplierAddressNeighborhood = findViewById(R.id.supplier_address_neighborhood);
        supplierAddressNumber = findViewById(R.id.supplier_address_number);
        supplierAddressCity = findViewById(R.id.supplier_address_city);
        supplierAddressCep = findViewById(R.id.supplier_address_cep);

        exitBtn = findViewById(R.id.exit_btn_supplier);
        exitBtn.setOnClickListener(v -> finish());

        createSupplier = findViewById(R.id.btn_register_supplier);
        createSupplier.setOnClickListener(v -> createSupplier());

    }

    private void createSupplier(){
        Address address = new Address.Builder()
                                        .street(supplierAddressStreet.getText().toString())
                                        .city(supplierAddressCity.getText().toString())
                                        .number(Integer.parseInt(supplierAddressNumber.getText().toString()))
                                        .cep(supplierAddressCep.getText().toString())
                                        .neighborhood(supplierAddressNeighborhood.getText().toString())
                                        .build();

        SupplierRequest supplierToSave = new SupplierRequest.Builder()
                                                .name(supplierName.getText().toString())
                                                .cnpj(supplierCNPJ.getText().toString())
                                                .email(supplierEmail.getText().toString())
                                                .phone(supplierPhone.getText().toString())
                                                .address(address).build();

        SupplierCall supplierCall = HttpClient.getInstance();

        Call<SupplierResponse> backEndResponse = supplierCall.createSuppliers(supplierToSave);

        backEndResponse.enqueue(new Callback<SupplierResponse>() {
            @Override
            public void onResponse(Call<SupplierResponse> call, Response<SupplierResponse> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Fornecedor criado com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar fornecedor", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro no backend", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SupplierResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Servidor fora do ar", Toast.LENGTH_LONG).show();
            }
        });
    }
}
