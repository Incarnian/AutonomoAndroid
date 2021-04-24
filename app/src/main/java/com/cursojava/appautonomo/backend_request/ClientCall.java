package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ClientRequest;
import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientCall {
    @GET("/users/{id}/clients")
    Call<List<ClientResponse>> readClients(@Path(value = "id") Long id);

    @POST("/users/{id}/clients")
    Call<ClientResponse> createClient(@Path(value = "id") Long id, @Body ClientRequest clientRequest);
}
