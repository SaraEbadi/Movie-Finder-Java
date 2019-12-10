package com.example.moviefinden.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.moviefinden.utils.FragmentChangeListener;
import com.example.moviefinden.R;

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
