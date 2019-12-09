package com.example.moviefinden.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviefinden.Constatnt;
import com.example.moviefinden.FragmentChangeListener;
import com.example.moviefinden.IMovieOnItemListener;
import com.example.moviefinden.MovieListDiffutils;
import com.example.moviefinden.R;
import com.example.moviefinden.view.adapter.MovieListAdapter;
import com.example.moviefinden.service.model.DetailsModel;
import com.example.moviefinden.service.model.MovieModel;
import com.example.moviefinden.service.model.ResultSearch;
import com.example.moviefinden.service.repository.GenerateRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity implements FragmentChangeListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commitNow();


    }


//    public void fetchData() {
//        Call<MovieModel> request = generateRetrofit.apiClient().getSearchMovie(Constatnt.API_KEY, edtMovieSearch.getText().toString());
//        request.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, final Response<MovieModel> response) {
//                if (response.isSuccessful()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            resultSearchList = response.body().getResultSearchList();
//                            movieListAdapter.submitList(resultSearchList);
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//                Log.i("onFailure", "run: " + t.getMessage());
//            }
//        });
//    }
//
//
//


    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment)
                .addToBackStack(fragment.toString())
                .commitNow();
    }
}
