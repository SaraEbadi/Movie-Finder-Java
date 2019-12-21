package com.example.moviefinden.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviefinden.base.Constatnt;
import com.example.moviefinden.models.DetailsModel;
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
    private DetailsModel detailsModel;
    private MutableLiveData<List<ResultSearch>> mutableLiveDataResultSearch = new MutableLiveData<>();
    private MutableLiveData<DetailsModel> mutableLiveDataDetailsModel = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final RetrofitInstance retrofitInstance;

    public ConcatRepository() {
        this.retrofitInstance = new RetrofitInstance();
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
                        mutableLiveDataResultSearch.postValue(resultSearchList);
                    }
                })
        );
        return mutableLiveDataResultSearch;
    }


    public MutableLiveData<DetailsModel> getDetailMovie(int id) {
        Observable<DetailsModel> detailsModelObservable = retrofitInstance.apiClient().getDetailsMovie(id, Constatnt.API_KEY);
        compositeDisposable.add(detailsModelObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<DetailsModel>() {
                    @Override
                    public void onNext(DetailsModel model) {
                        detailsModel = model;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mutableLiveDataDetailsModel.postValue(detailsModel);
                    }
                })
        );

        return mutableLiveDataDetailsModel;
    }


    public void clear() {
        compositeDisposable.clear();
    }


}
