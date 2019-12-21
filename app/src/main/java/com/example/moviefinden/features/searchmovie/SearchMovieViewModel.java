package com.example.moviefinden.features.searchmovie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviefinden.data.ConcatRepository;
import com.example.moviefinden.models.ResultSearch;
import com.example.moviefinden.retrofit.RetrofitInstance;

import java.util.List;

public class SearchMovieViewModel extends AndroidViewModel {


    private ConcatRepository concatRepository;

    public SearchMovieViewModel(@NonNull Application application) {
        super(application);
        this.concatRepository = new ConcatRepository(new RetrofitInstance());
    }

    public LiveData<List<ResultSearch>> getAllMovie(String keySearchMovie) {
        return concatRepository.getMovieSearch(keySearchMovie);
    }

    public void clear() {
        concatRepository.clear();
    }


}
