package com.example.moviefinden.view.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.moviefinden.utils.FragmentChangeListener;
import com.example.moviefinden.IMovieOnItemListener;
import com.example.moviefinden.MovieListDiffutils;
import com.example.moviefinden.R;
import com.example.moviefinden.service.model.ResultSearch;
import com.example.moviefinden.service.repository.GenerateRetrofit;
import com.example.moviefinden.view.adapter.MovieListAdapter;
import com.example.moviefinden.viewmodel.MovieViewModel;

import java.util.List;

import retrofit2.Retrofit;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class MainFragment extends Fragment implements IMovieOnItemListener {

    Fragment context;
    private RecyclerView movieRecyclerView;
    private EditText edtMovieSearch;
    private ImageView imgMovieSearch;
    private MovieListAdapter movieListAdapter;
    List<ResultSearch> resultSearchList;
    private GenerateRetrofit generateRetrofit;
    MovieViewModel movieViewModel;



    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this;
        init(view);



    }



    @Override
    public void onClickListener(View view, int position) {
        Bundle bundle =new Bundle();
        bundle.putInt("movieId",resultSearchList.get(position).getId());
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        Fragment fr=new DetailsFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }


    public void generateMovieLists() {
        movieListAdapter = new MovieListAdapter(new MovieListDiffutils());
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        movieRecyclerView.setAdapter(movieListAdapter);

    }


    public void init(View view) {
        movieRecyclerView = view.findViewById(R.id.movieRecyclerView);
        edtMovieSearch = view.findViewById(R.id.edtGetMovieSearch);
        imgMovieSearch = view.findViewById(R.id.imgSearchMovie);
        generateMovieLists();
        Retrofit.Builder builder = new Retrofit.Builder();
        generateRetrofit = new GenerateRetrofit(builder);
        movieViewModel = ViewModelProviders.of(context).get(MovieViewModel.class);

        imgMovieSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(((Activity) getContext()).getWindow().getCurrentFocus().getWindowToken(), 0);
                movieViewModel.searchMovies(edtMovieSearch.getText().toString(),generateRetrofit).observe(context, new Observer<List<ResultSearch>>() {
                    @Override
                    public void onChanged(List<ResultSearch> resultSearches) {
                        movieListAdapter.submitList(resultSearches);
                    }
                });
            }
        });


    }
}

