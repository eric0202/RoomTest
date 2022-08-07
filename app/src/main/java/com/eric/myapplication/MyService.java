package com.eric.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyService {

    @GET("/saying/get")
    Call<Response> getSayingForDay();
}
