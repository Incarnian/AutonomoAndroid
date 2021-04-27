package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductCall {
    @GET("/users/{id}/products")
    Call<List<ProductResponse>> readProducts(@Path(value = "id") Long id);

    @POST("/users/{id}/products")
    Call<ProductResponse> createProduct(@Path(value = "id") Long id, @Body ProductRequest productRequest);

    @DELETE("/user/{id}/products")
    Call<ProductResponse> deleteProduct(@Path(value = "id") Long id, @Body ProductRequest productRequest);

    @PUT("/user/{id}/products")
    Call<ProductResponse> editProduct(@Path(value = "id") Long id, @Body ProductRequest productRequest);
}
