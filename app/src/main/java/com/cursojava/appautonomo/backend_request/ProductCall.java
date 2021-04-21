package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProductCall {
    @GET("/users/2/products")
    Call<List<ProductResponse>> readProducts();

    @POST("/users/2/products")
    Call<ProductResponse> createProduct(@Body ProductRequest productRequest);
}
