package com.example.moviefinden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviefinden.adapter.MovieListAdapter;
import com.example.moviefinden.model.DetailsModel;
import com.example.moviefinden.model.Genres;
import com.example.moviefinden.model.MovieModel;
import com.example.moviefinden.model.ResultSearch;
import com.example.moviefinden.retrofit.GenerateRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity implements IMovieOnItemListener {

    RecyclerView movieRecyclerView;
    EditText edtMovieSearch;
    ImageView imgMovieSearch;
    MovieListAdapter movieListAdapter;
    List<ResultSearch> resultSearchList;
    GenerateRetrofit generateRetrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


    public void fetchData() {
        Call<MovieModel> request = generateRetrofit.apiClient().getSearchMovie(Constatnt.API_KEY, edtMovieSearch.getText().toString());
        request.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, final Response<MovieModel> response) {
                if (response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultSearchList = response.body().getResultSearchList();
                            movieListAdapter.submitList(resultSearchList);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.i("onFailure", "run: " + t.getMessage());
            }
        });
    }

    public void generateMovieLists() {
        movieListAdapter = new MovieListAdapter(new MovieListDiffutils());
        movieRecyclerView.setAdapter(movieListAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
        movieListAdapter.setOnClickListener(this);


    }

    public void init() {
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        edtMovieSearch = findViewById(R.id.edtGetMovieSearch);
        imgMovieSearch = findViewById(R.id.imgSearchMovie);

        Retrofit.Builder builder = new Retrofit.Builder();
        generateRetrofit = new GenerateRetrofit(builder);
        imgMovieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateMovieLists();
                fetchData();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });


    }




    @Override
    public void onClickListener(View view, final int position) {

        Call<DetailsModel> requestDetails = generateRetrofit.apiClient().getDetailsMovie(resultSearchList.get(position).getId(),Constatnt.API_KEY);
        requestDetails.enqueue(new Callback<DetailsModel>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                DetailsModel detailsModel = response.body();
                if (detailsModel == null) {
                    Toast.makeText(MainActivity.this, "اطلاعات کاملی برای این فیلم وجود ندارد.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(MainActivity.this, DetailsMovieActivity.class);

                intent.putExtra("backDropPath", detailsModel.getBackDropPath() != null ? detailsModel.getBackDropPath() : "");
                intent.putExtra("genres", detailsModel.getGenres().get(0).getName()!= null ? detailsModel.getGenres().get(0).getName() : "");
                intent.putExtra("budget", detailsModel.getBudget() != 0 ? detailsModel.getBudget() : "");
                intent.putExtra("originalLanguage", detailsModel.getOriginalLanguage() != null ? detailsModel.getOriginalLanguage() : "");
                intent.putExtra("originalTitle", detailsModel.getOriginalTitle()!= null ? detailsModel.getOriginalTitle() : "");
                intent.putExtra("overview", detailsModel.getOverview() != null ? detailsModel.getOverview() : "");
                intent.putExtra("revenue", detailsModel.getRevenue() != 0 ? detailsModel.getRevenue() : "");
                intent.putExtra("voteAverage", detailsModel.getVoteAverage() != 0 ? detailsModel.getVoteAverage() : "");
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {
                Log.i("onFailure", "onFailure: " + t.getMessage());
            }
        });


    }
}
