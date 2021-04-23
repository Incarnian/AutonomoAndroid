package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.ProductRequest;
import com.cursojava.appautonomo.model.ProductResponse;
import com.cursojava.appautonomo.model.SupplierRequest;
import com.cursojava.appautonomo.model.SupplierResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SupplierCall {
    @GET("/users/2/suppliers")
    Call<List<SupplierResponse>> getSuppliers();

    @POST("/users/2/supplierss")
    Call<SupplierResponse> createSuppliers (@Body SupplierRequest supplierRequest);
}
