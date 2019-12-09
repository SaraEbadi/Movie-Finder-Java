package com.example.moviefinden.viewmodel;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviefinden.Constatnt;
import com.example.moviefinden.service.model.DetailsModel;
import com.example.moviefinden.service.repository.GenerateRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class DetailsViewModel extends AndroidViewModel {

    private MutableLiveData<DetailsModel> detailsLiveData;


    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<DetailsModel> getDetails(int id, GenerateRetrofit generateRetrofit) {
        if (detailsLiveData == null) {
            detailsLiveData = new MutableLiveData<>();
            Call<DetailsModel> requestDetails = generateRetrofit.apiClient().getDetailsMovie(id, Constatnt.API_KEY);
            requestDetails.enqueue(new Callback<DetailsModel>() {
                @Override
                @EverythingIsNonNull
                public void onResponse(retrofit2.Call<DetailsModel> call, Response<DetailsModel> response) {
                    detailsLiveData.setValue(response.body());
                    if (response.body() == null) {
                        Toast.makeText(getApplication(), "اطلاعات کاملی برای این فیلم وجود ندارد.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onFailure(Call<DetailsModel> call, Throwable t) {
                    Log.i("onFailure", "onFailure: " + t.getMessage());
                }
            });

        }

        return detailsLiveData;
    }

}
