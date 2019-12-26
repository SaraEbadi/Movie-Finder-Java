package com.example.moviefinden.features.detailsmovie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviefinden.data.ConcatRepository;
import com.example.moviefinden.models.DetailsModel;
import com.example.moviefinden.retrofit.RetrofitInstance;

public class DetailsMovieViewModel extends AndroidViewModel {

    private RetrofitInstance retrofitInstance;
    private ConcatRepository concatRepository;


    public DetailsMovieViewModel(@NonNull Application application) {
        super(application);
        concatRepository = new ConcatRepository();
    }


    public LiveData<DetailsModel> getDetails(int id) {
        return concatRepository.getDetailMovie(id);

    }

    public void clear(){
        concatRepository.clear();

    }

}
