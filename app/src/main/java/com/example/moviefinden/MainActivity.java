package com.example.moviefinden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moviefinden.adapter.MovieListAdapter;
import com.example.moviefinden.model.MovieModel;
import com.example.moviefinden.model.ResultSearch;
import com.example.moviefinden.retrofit.GenerateRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView movieRecyclerView;
    EditText edtMovieSearch;
    ImageView imgMovieSearch;
    MovieListAdapter movieListAdapter;
    List<ResultSearch> resultSearchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }




    public void fetchData(){
        Retrofit.Builder builder = new Retrofit.Builder();
        GenerateRetrofit generateRetrofit = new GenerateRetrofit(builder);
        Call<MovieModel> request = generateRetrofit.apiClient().getMovieDetails(Constatnt.API_KEY,edtMovieSearch.getText().toString());
        request.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, final Response<MovieModel> response) {
                if(response.isSuccessful()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultSearchList = response.body().getResultSearchList();
                            movieListAdapter.submitList(resultSearchList);
                            Toast.makeText(MainActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                Log.i("onFailure", "run: "+t.getMessage());
            }
        });
    }

    public void generateMovieLists(){
        movieListAdapter = new MovieListAdapter(new MovieListDiffutils());
        movieRecyclerView.setAdapter(movieListAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));


    }

    public void init(){
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        edtMovieSearch = findViewById(R.id.edtGetMovieSearch);
        imgMovieSearch = findViewById(R.id.imgSearchMovie);
        imgMovieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "hiiiiii", Toast.LENGTH_SHORT).show();
                generateMovieLists();
                fetchData();
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }
        });


    }
}
