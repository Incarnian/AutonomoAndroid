package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.SupplierRequest;
import com.cursojava.appautonomo.model.SupplierResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SupplierCall {
    @GET("/users/{id}/suppliers")
    Call<List<SupplierResponse>> getSuppliers(@Header("Authorization") String token, @Path(value = "id") Long id);

    @POST("/users/{id}/suppliers")
    Call<SupplierResponse> createSuppliers (@Header("Authorization") String token,@Path(value = "id") Long id, @Body SupplierRequest supplierRequest);
}
