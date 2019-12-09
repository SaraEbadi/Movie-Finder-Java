package com.example.moviefinden.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviefinden.Constatnt;
import com.example.moviefinden.service.model.MovieModel;
import com.example.moviefinden.service.model.ResultSearch;
import com.example.moviefinden.service.repository.GenerateRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {


    private MutableLiveData<List<ResultSearch>> data;

    public MovieViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<ResultSearch>> searchMovies(String key, GenerateRetrofit generateRetrofit) {
        if (data == null) {
            data = new MutableLiveData<>();

            Call<MovieModel> request = generateRetrofit.apiClient().getSearchMovie(Constatnt.API_KEY, key);
            request.enqueue(new Callback<MovieModel>() {
                @Override
                public void onResponse(Call<MovieModel> call, final Response<MovieModel> response) {
                    if (response.isSuccessful()) {
                        data.setValue(response.body().getResultSearchList());
                    }
                }

                @Override
                public void onFailure(Call<MovieModel> call, Throwable t) {
                    Log.i("onFailure", "run: " + t.getMessage());
                }
            });
        }

        return data;
    }


}
