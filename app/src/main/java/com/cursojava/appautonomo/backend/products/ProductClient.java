package com.cursojava.appautonomo.backend.products;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductClient {
    @GET("/users/2/products")
    Call<List<ProductResponse>> getProducts();

    @POST("/users/2/products")
    Call<ProductResponse> saveProduct(@Body ProductRequest productRequest);
}
