package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductClient {
    @GET("/users/4/products")
    Call<List<ProductResponse>> getProducts();

    @POST("/users/4/products")
    Call<ProductResponse> saveProduct(@Body ProductRequest productRequest);
}
