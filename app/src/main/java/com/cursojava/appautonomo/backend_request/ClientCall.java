package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ClientRequest;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ClientCall {
    @GET("/users/4/clients")
    Call<List<ClientResponse>> readClients();

    @POST("/users/4/clients")
    Call<ClientResponse> createClient(@Body ClientRequest clientRequest);
}
