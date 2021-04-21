package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static BackendRequests instance;

    public static final String BASE_URL = BuildConfig.BASE_URL;

    public static BackendRequests getInstance() {
        return instance == null ? initialize() : instance;
    }

    public static BackendRequests initialize() {
        instance = buildHttpClient();
        return instance;
    }

    private static BackendRequests buildHttpClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BackendRequests.class);
    }
}
