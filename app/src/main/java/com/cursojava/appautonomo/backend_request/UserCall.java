package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ClientResponse;
import com.cursojava.appautonomo.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UserCall {

    @GET("/users/{id}")
    Call<UserResponse> getUserDetails(@Header("Authorization") String token, @Path(value = "id") Long id);

}
