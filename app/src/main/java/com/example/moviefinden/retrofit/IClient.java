package com.example.moviefinden.retrofit;

import com.example.moviefinden.model.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IClient {

    @GET("search/movie")
    Call<MovieModel> getMovieDetails(
            @Query("api_key") String apiKey,
            @Query("query") String query);
}
