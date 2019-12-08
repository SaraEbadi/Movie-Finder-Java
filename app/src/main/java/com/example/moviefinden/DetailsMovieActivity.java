package com.example.moviefinden;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsMovieActivity extends AppCompatActivity {

    ImageView imgMovie;
    TextView txtTitle;
    TextView txtRateAverage;
    TextView txtOverView;
    TextView txtGenres;
    TextView txtBudget;
    TextView txtRevenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        init();




    }

    @SuppressLint("SetTextI18n")
    public void init(){
        txtTitle = findViewById(R.id.txtTitle);
        txtRateAverage = findViewById(R.id.txtRateAverage);
        txtOverView = findViewById(R.id.txtOverView);
        imgMovie = findViewById(R.id.imgMovie);
        txtGenres = findViewById(R.id.movieGenres);
        txtRevenue = findViewById(R.id.txtRevenue);
        txtBudget = findViewById(R.id.txtBudget);
        Intent intent = getIntent();

        intent.getIntExtra("budget",11);
        intent.getStringExtra("originalLanguage");
        intent.getIntExtra("revenue",22);
        Picasso.with(DetailsMovieActivity.this)
                .load("https://image.tmdb.org/t/p/original" + intent.getStringExtra("backDropPath"))
                .centerInside()
                .fit()
                .into(imgMovie);
        txtTitle.setText(intent.getStringExtra("originalTitle"));
        txtRateAverage.setText(String.valueOf(intent.getDoubleExtra("voteAverage",8.5)));
        txtOverView.setText(intent.getStringExtra("overview"));
        txtGenres.setText(intent.getStringExtra("genres"));
        txtRevenue.setText(intent.getIntExtra("revenue",25500) + " $");
        txtBudget.setText(intent.getIntExtra("budget",2568845)+ " $");




    }
}
