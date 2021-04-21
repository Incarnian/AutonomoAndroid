package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ClientRequest;
import com.cursojava.appautonomo.model.ClientResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientCall {
    @POST("/users/2/clients")
    Call<ClientResponse> createClient(@Body ClientRequest clientRequest);
}
