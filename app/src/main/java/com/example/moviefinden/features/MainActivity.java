package com.example.moviefinden.features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.moviefinden.R;
import com.example.moviefinden.models.DetailsModel;

import java.util.Observable;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.container,MainFragment.newInstance()).commitNow();


//        NavHostFragment.findNavController(MainFragment.newInstance());

//        navController = Navigation.findNavController(view);

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


}
