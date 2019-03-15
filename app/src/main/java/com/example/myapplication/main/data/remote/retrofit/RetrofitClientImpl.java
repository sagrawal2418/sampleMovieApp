package com.example.myapplication.main.data.remote.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.myapplication.main.Constants.BASE_URL;

public class RetrofitClientImpl implements RetrofitClient {

    @Override
    public Retrofit createRetrofit() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}