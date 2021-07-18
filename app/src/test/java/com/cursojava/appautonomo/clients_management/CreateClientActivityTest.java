package com.cursojava.appautonomo.clients_management;

import android.content.SharedPreferences;

import com.cursojava.appautonomo.backend_request.ClientCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.model.Address;
import com.cursojava.appautonomo.model.ClientRequest;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CreateClientActivityTest  {

    private List<ClientRequest> clients;
    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    private long idClient01 = 0L, idClient02 = 1L;

    @Before
    public void setUp() {

        Address address01 = new Address.Builder().
                 street("Rua teste 01")
                .city("Cidade teste 01")
                .number(01)
                .cep("00.000-001")
                .neighborhood("Vizinhança teste 01")
                .build();

        ClientRequest clientToSave01 = new ClientRequest.Builder()
                .name("Cliente 01")
                .email("cliente01@mail.com")
                .cpf("000.000.000-01")
                .address(address01)
                .phone("(11)0000-0001")
                .build();

        Address address02 = new Address.Builder().
                street("Rua teste 02")
                .city("Cidade teste 02")
                .number(01)
                .cep("00.000-002")
                .neighborhood("Vizinhança teste 02")
                .build();

        ClientRequest clientToSave02 = new ClientRequest.Builder()
                .name("Cliente 02")
                .email("cliente02@mail.com")
                .cpf("000.000.000-02")
                .address(address02)
                .phone("(22)0000-0002")
                .build();

         List<ClientRequest> list = new ArrayList<>();
         list.add(clientToSave01);
         list.add(clientToSave02);

        clients = list;

    }

    /**
     * Creating clients test calling the backend
     *
     * @author Nicolas
     */

    @Test
    public void createClientTest(){

        /* ========= Run ============== */

        String token01 = "e2ffdsgfdjgjd5";
        String token02 = "gkjfdnhdgkln3f";

        ClientCall clientCall01 = HttpClient.getInstance();
        Call<ClientResponse> backResponse01 = clientCall01.createClient(token01,
                sp.getLong(Constants.USER_ID, idClient01),
                clients.get(Integer.parseInt(Constants.USER_ID)));

        ClientCall clientCall02 = HttpClient.getInstance();
        Call<ClientResponse> backResponse02 = clientCall02.createClient(token02,
                sp.getLong(Constants.USER_ID, idClient02),
                clients.get(Integer.parseInt(Constants.USER_ID)));

        /* =========== Check =========== */

        backResponse01.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                assertTrue(response.code() == 201);
                assertThat(clients.get(Integer.parseInt(Constants.USER_ID)), is(idClient01));
                assertNotNull(call);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                assertNull(call);
            }
        });

        backResponse02.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                assertTrue(response.code() == 201);
                assertThat(clients.get(Integer.parseInt(Constants.USER_ID)), is(idClient02));
                assertNotNull(call);
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                assertNull(call);
            }
        });
    }

    @After
    public void tearDown()  {
        clients.clear();
    }
}