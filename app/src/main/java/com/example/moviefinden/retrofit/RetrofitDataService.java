package com.example.moviefinden.retrofit;

import com.example.moviefinden.models.DetailsModel;
import com.example.moviefinden.models.MovieModel;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitDataService {

    @GET("search/movie")
    Observable<MovieModel> getSearchMovie(
            @Query("api_key") String apiKey,
            @Query("query") String query
    );

    @GET("movie/{movie_id}")
    Call<DetailsModel> getDetailsMovie(
        @Path("movie_id") int movieId,
        @Query("api_key") String apiKey
    );

}
