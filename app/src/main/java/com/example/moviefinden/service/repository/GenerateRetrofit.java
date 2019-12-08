package com.example.moviefinden.service.repository;

import com.example.moviefinden.Constatnt;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenerateRetrofit {

    private Retrofit retrofit;

    public GenerateRetrofit(Retrofit.Builder retrofit) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        this.retrofit = retrofit
                .client(client)
                .baseUrl(Constatnt.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public IClient apiClient() {
        return retrofit.create(IClient.class);
    }
}
