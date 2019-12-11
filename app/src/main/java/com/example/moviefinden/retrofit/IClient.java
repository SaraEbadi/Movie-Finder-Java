package com.example.moviefinden.retrofit;

import com.example.moviefinden.models.DetailsModel;
import com.example.moviefinden.models.MovieModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IClient {

    @GET("search/movie")
    Call<MovieModel> getSearchMovie(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("movie/{movie_id}")
    Call<DetailsModel> getDetailsMovie(
        @Path("movie_id") int movieId,
        @Query("api_key") String apiKey
    );

}
