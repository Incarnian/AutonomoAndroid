package com.cursojava.appautonomo.suppliers_management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.adapters.SuppliersAdapter;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.backend_request.SupplierCall;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.SupplierResponse;
import com.cursojava.appautonomo.products_management.ProductsActivity;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuppliersActivity extends AppCompatActivity {

    private RecyclerView suppliersRecyclerView;
    private List<SupplierResponse> suppliers;
    private ProgressDialog progressDialog;
    private ImageView addSuppliersBtn;
    private ImageView btnExit;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);

        suppliersRecyclerView = findViewById(R.id.recycler_suppliers);


        addSuppliersBtn = findViewById(R.id.add_supplier_btn);
        addSuppliersBtn.setOnClickListener(v -> onAddNewSupplier());
        //suppliersRecyclerView.setAdapter();

        progressDialog = new ProgressDialog(SuppliersActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(v -> {
            finish();
        });
        getSuppliers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSuppliers();
    }

    private void getSuppliers() {
        SupplierCall requests = HttpClient.getInstance();

        Call<List<SupplierResponse>> suppliersResponse = requests.getSuppliers(sp.getLong(Constants.USER_ID, 0));

        suppliersResponse.enqueue(new Callback<List<SupplierResponse>>() {
            @Override
            public void onResponse(Call<List<SupplierResponse>> call, Response<List<SupplierResponse>> response) {
                if(response.isSuccessful()) {
                    suppliers = response.body();
                    SuppliersAdapter suppliersAdapter = new SuppliersAdapter(suppliers);
                    suppliersRecyclerView.setAdapter(suppliersAdapter);
                    suppliersRecyclerView.setHasFixedSize(true);
                    progressDialog.dismiss();
                }
                else{
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(),"Não foi possível receber as informações do banco de dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SupplierResponse>> call, Throwable t) {
                System.out.println("ERRRRRRRROOOOOOOOOOOOOOOOOOOOO");
            }
        });
    }


    private void onAddNewSupplier() {
        Intent intent = new Intent(this, CreateSupplierActivity.class);
        startActivity(intent);
    }
}
