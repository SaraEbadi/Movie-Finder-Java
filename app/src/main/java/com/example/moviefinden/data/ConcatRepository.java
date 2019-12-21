package com.example.moviefinden.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviefinden.base.Constatnt;
import com.example.moviefinden.models.MovieModel;
import com.example.moviefinden.models.ResultSearch;
import com.example.moviefinden.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ConcatRepository {

    private List<ResultSearch> resultSearchList;
    private MutableLiveData<List<ResultSearch>> mutableLiveData = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final RetrofitInstance retrofitInstance;

    public ConcatRepository(RetrofitInstance retrofitInstance) {
        this.retrofitInstance = retrofitInstance;
    }

    public MutableLiveData<List<ResultSearch>> getMovieSearch(String keySearchMovie) {
        resultSearchList = new ArrayList<>();
        Observable<MovieModel> resultSearchObservable = retrofitInstance.apiClient().getSearchMovie(Constatnt.API_KEY, keySearchMovie);
        compositeDisposable.add(resultSearchObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MovieModel>() {
                    @Override
                    public void onNext(MovieModel movieModel) {
                        resultSearchList = movieModel.getResultSearchList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("rxjava", "run: " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        mutableLiveData.postValue(resultSearchList);
                    }
                })
        );
        return mutableLiveData;

    }


    public void clear() {
        compositeDisposable.clear();
    }


}
