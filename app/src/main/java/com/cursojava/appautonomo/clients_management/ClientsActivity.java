package com.cursojava.appautonomo.clients_management;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.adapters.ClientsAdapter;
import com.cursojava.appautonomo.backend_request.ClientCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.products_management.CreateProductActivity;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientsActivity extends AppCompatActivity {

    private RecyclerView clientsRecyclerView;
    private List<ClientResponse> clients;
    private ImageView addClientButton;
    private ImageView backClientButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        clientsRecyclerView = findViewById(R.id.recycler_clients);

        addClientButton = findViewById(R.id.add_client_btn);
        addClientButton.setOnClickListener(v -> onAddNewClient());

        backClientButton = findViewById(R.id.clients_back_arrow);
        backClientButton.setOnClickListener(v -> onBackClient());

        ClientCall requests = HttpClient.getInstance();

        Call<List<ClientResponse>> clientResponse = requests.readClients();

        clientResponse.enqueue(new Callback<List<ClientResponse>>() {
            @Override
            public void onResponse(Call<List<ClientResponse>> call, Response<List<ClientResponse>> response) {
                if (response.isSuccessful()) {
                    clients = response.body();
                    ClientsAdapter clientsAdapter = new ClientsAdapter(clients);
                    clientsRecyclerView.setAdapter(clientsAdapter);
                    clientsRecyclerView.setHasFixedSize(true);


                }
            }

            @Override
            public void onFailure(Call<List<ClientResponse>> call, Throwable t) {
                System.out.println("Alguma coisa deu errado");
            }
        });
    }
        private void onAddNewClient() {
            Intent intent = new Intent(this, CreateClientActivity.class);
            startActivity(intent);
        }

        private void onBackClient(){
        finish();

        }

    }
