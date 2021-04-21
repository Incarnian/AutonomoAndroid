package com.cursojava.appautonomo.backend_request;

import com.cursojava.appautonomo.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    private static BackendCalls instance;

    public static final String BASE_URL = BuildConfig.BASE_URL;

    public static BackendCalls getInstance() {
        return instance == null ? initialize() : instance;
    }

    public static BackendCalls initialize() {
        instance = buildHttpClient();
        return instance;
    }

    private static BackendCalls buildHttpClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BackendCalls.class);
    }
}
