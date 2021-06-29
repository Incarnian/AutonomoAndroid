package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.JwtToken;
import com.cursojava.appautonomo.model.Login;
import com.cursojava.appautonomo.model.UserRequest;
import com.cursojava.appautonomo.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthenticationCall {
    /* SIGN UP */
    @POST("/signUp")
    Call<UserResponse> createUser(@Body UserRequest userRequest);

    /*SIGN IN*/
    @POST("/signIn")
    Call<JwtToken> signIn(@Body Login credentials);
}
