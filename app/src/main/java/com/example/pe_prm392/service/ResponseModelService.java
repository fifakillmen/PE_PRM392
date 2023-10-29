package com.example.pe_prm392.service;

import com.example.pe_prm392.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResponseModelService {
    @GET("users")
    Call<ResponseModel> getUsers(@Query("page") int page);
}
