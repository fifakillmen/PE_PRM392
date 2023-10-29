package com.example.pe_prm392.configuration;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServiceConfiguration {
    private Retrofit retrofit;


    public RetrofitServiceConfiguration() {
        initializeRetrofit();
    }

    private void initializeRetrofit() {
        retrofit= new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
