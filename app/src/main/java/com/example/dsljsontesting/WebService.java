package com.example.dsljsontesting;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebService {
    @GET(".")
    public Call<GetData> getData();
}
