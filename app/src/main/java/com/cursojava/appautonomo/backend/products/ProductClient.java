package com.cursojava.appautonomo.backend.products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductClient {
    @GET("/users/1/products")
    Call<List<ProductResponse>> getProducts();
}
