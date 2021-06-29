package com.cursojava.appautonomo.clients_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cursojava.appautonomo.R;
import com.cursojava.appautonomo.backend_request.ClientCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.model.Address;
import com.cursojava.appautonomo.model.ClientRequest;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateClientActivity extends AppCompatActivity {

    private EditText clientName;
    private EditText clientEmail;
    private EditText clientCpf;
    private EditText clientPhone;
    private EditText clientAddressStreet;
    private EditText clientAddressNeighborhood;
    private EditText clientAddressNumber;
    private EditText clientAddressCity;
    private EditText clientAddressCep;
//    private EditText clientAddressUf;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    private ImageView exitBtn;
    private Button createClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_client);

        // Getting fields
        clientName = findViewById(R.id.client_name);
        clientEmail = findViewById(R.id.client_email);
        clientCpf = findViewById(R.id.client_cpf);
        clientPhone = findViewById(R.id.client_phone);
        clientAddressStreet = findViewById(R.id.client_address_street);
        clientAddressNeighborhood = findViewById(R.id.client_address_neighborhood);
        clientAddressNumber = findViewById(R.id.client_address_number);
        clientAddressCity = findViewById(R.id.client_address_city);
        clientAddressCep = findViewById(R.id.client_address_cep);
//        clientAddressUf = findViewById(R.id.client_address_uf);

        // Getting buttons
        exitBtn = findViewById(R.id.exit_btn);
        exitBtn.setOnClickListener(v -> finish());

        createClient = findViewById(R.id.btn_register);
        createClient.setOnClickListener(v -> createClient());
    }

    private void createClient() {

        Address clientAddress = new Address.Builder()
                                            .street(clientAddressStreet.getText().toString())
                                            .city(clientAddressCity.getText().toString())
                                            .number(Integer.parseInt(clientAddressNumber.getText().toString()))
                                            .cep(clientAddressCep.getText().toString())
                                            .neighborhood(clientAddressNeighborhood.getText().toString())
                                            .build();

        ClientRequest clientToSave = new ClientRequest.Builder()
                                            .name(clientName.getText().toString())
                                            .email(clientEmail.getText().toString())
                                            .cpf(clientCpf.getText().toString())
                                            .address(clientAddress)
                                            .phone(clientPhone.getText().toString())
                                            .build();

        ClientCall clientCall = HttpClient.getInstance();
        String token = sp.getString(Constants.FULL_TOKEN, "");
        Call<ClientResponse> backEndResponse = clientCall.createClient(token, sp.getLong(Constants.USER_ID, 0),clientToSave);

        backEndResponse.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Cliente criado com sucesso!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao cadastrar cliente", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Erro no backend", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Servidor fora do ar", Toast.LENGTH_LONG).show();
            }
        });

    }
}