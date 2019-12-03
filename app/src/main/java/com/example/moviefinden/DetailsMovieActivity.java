package com.example.moviefinden;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);
        init();




    }

    public void init(){
        txtTitle = findViewById(R.id.txtTitle);
        txtRateAverage = findViewById(R.id.txtRateAverage);
        txtOverView = findViewById(R.id.txtOverView);
        imgMovie = findViewById(R.id.imgMovie);
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




    }
}
