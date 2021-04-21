package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.model.SupplierResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SupplierClient {
    @GET("/users/4/suppliers")
    Call<List<SupplierResponse>> getSuppliers();
}
