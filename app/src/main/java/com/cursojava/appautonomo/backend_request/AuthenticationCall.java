package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.Login;
import com.cursojava.appautonomo.model.UserRequest;
import com.cursojava.appautonomo.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationCall {
    /* SIGN UP */
    @POST("/users")
    Call<UserResponse> createUser(@Body UserRequest userRequest);

    /*SIGN IN*/
    @POST("/auth/login")
    Call<UserResponse> signIn(@Body Login credentials);
}
