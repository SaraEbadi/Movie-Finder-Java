package com.example.moviefinden.features.detailsmovie;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviefinden.R;
import com.example.moviefinden.models.DetailsModel;
import com.example.moviefinden.retrofit.RetrofitInstance;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;

public class DetailsFragment extends Fragment {

    ImageView imgMovie;
    TextView txtTitle;
    TextView txtRateAverage;
    TextView txtOverView;
    TextView txtGenres;
    TextView txtBudget;
    TextView txtRevenue;


    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance() {
        Bundle args = new Bundle();
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        Bundle bundle = getArguments();
        int movieID = bundle.getInt("movieId", 0);

        DetailsMovieViewModel detailsViewModel = ViewModelProviders.of(this).get(DetailsMovieViewModel.class);
        detailsViewModel.getDetails(movieID).observe(this, new Observer<DetailsModel>() {
            @Override
            public void onChanged(DetailsModel detailsModel) {
                Picasso.get()
                        .load("https://image.tmdb.org/t/p/original" + detailsModel.getBackDropPath())
                        .centerInside()
                        .fit()
                        .into(imgMovie);
                txtTitle.setText(detailsModel.getOriginalTitle());
                txtRateAverage.setText(String.valueOf(detailsModel.getVoteAverage()));
                txtOverView.setText(detailsModel.getOverview());
                txtGenres.setText(detailsModel.getGenres().get(0).getName());
                txtRevenue.setText(detailsModel.getRevenue() + " $");
                txtBudget.setText(detailsModel.getBudget() + " $");
            }
        });

    }

    public void init(View view) {
        txtTitle = view.findViewById(R.id.txtTitle);
        txtRateAverage = view.findViewById(R.id.txtRateAverage);
        txtOverView = view.findViewById(R.id.txtOverView);
        imgMovie = view.findViewById(R.id.imgMovie);
        txtGenres = view.findViewById(R.id.movieGenres);
        txtRevenue = view.findViewById(R.id.txtRevenue);
        txtBudget = view.findViewById(R.id.txtBudget);
    }


}
