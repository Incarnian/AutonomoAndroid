package com.cursojava.appautonomo.clients_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.adapters.ClientsAdapter;
import com.cursojava.appautonomo.backend_request.ClientCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsActivity extends AppCompatActivity {

    private RecyclerView clientsRecyclerView;
    private List<ClientResponse> clients;
    private ImageView addClientButton;
    private ImageView backClientButton;
    private ProgressDialog progressDialog;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        clientsRecyclerView = findViewById(R.id.recycler_clients);

        addClientButton = findViewById(R.id.add_client_btn);
        addClientButton.setOnClickListener(v -> onAddNewClient());

        backClientButton = findViewById(R.id.exit);
        backClientButton.setOnClickListener(v -> onBackClient());

        //Criando o progress dialog quando entra na tela
        progressDialog = new ProgressDialog(ClientsActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        getClients();

    }

    private void getClients() {
        ClientCall requests = HttpClient.getInstance();
        String token = sp.getString(Constants.FULL_TOKEN, "");
        System.out.println("TOKEN CLIENTS: " + token);
        Call<List<ClientResponse>> clientResponse = requests.readClients( token ,sp.getLong(Constants.USER_ID, 0L));

        clientResponse.enqueue(new Callback<List<ClientResponse>>() {
            @Override
            public void onResponse(Call<List<ClientResponse>> call, Response<List<ClientResponse>> response) {
                if (response.isSuccessful()) {
                    clients = response.body();
                    ClientsAdapter clientsAdapter = new ClientsAdapter(clients);
                    clientsRecyclerView.setAdapter(clientsAdapter);
                    clientsRecyclerView.setHasFixedSize(true);

                    //Fecha o progressDialog quando obt??m resposta
                    progressDialog.dismiss();

                }
                else{
                    //Fecha o progressDialog caso n??o obter resposta, sai da tela e mostra um Toast
                    progressDialog.dismiss();
                    finish();
                    Toast.makeText(getApplicationContext(),"N??o foi poss??vel receber as informa????es do banco de dados",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ClientResponse>> call, Throwable t) {
                System.out.println("Alguma coisa deu errado");
                Toast.makeText(getApplicationContext(),"N??o foi poss??vel obter as informa????es necess??rias", Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getClients();
    }

    private void onAddNewClient() {
        Intent intent = new Intent(this, CreateClientActivity.class);
        startActivity(intent);
    }

    private void onBackClient(){
        finish();
    }
}
