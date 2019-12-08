package com.example.moviefinden.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviefinden.IMovieOnItemListener;
import com.example.moviefinden.R;
import com.example.moviefinden.model.ResultSearch;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends ListAdapter<ResultSearch, MovieListAdapter.MovieViewHolder> {

    List<ResultSearch> resultSearchList;
    IMovieOnItemListener movieOnClickListener;

    public MovieListAdapter(@NonNull DiffUtil.ItemCallback<ResultSearch> diffCallback) {
        super(diffCallback);
    }


    @Override
    public void submitList(@Nullable List<ResultSearch> list) {
        super.submitList(list);
        resultSearchList = list;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.titleMovie.setText(resultSearchList.get(position).getOriginalTitle());
        holder.voteAverage.setText(String.valueOf(resultSearchList.get(position).getVoteAverage()));
        holder.releaseDate.setText(resultSearchList.get(position).getReleaseDate());
        Picasso.with(holder.imgPosterPath.getContext())
                .load("https://image.tmdb.org/t/p/original"+resultSearchList.get(position).getPosterPath())
                .centerInside()
                .fit()
                .into(holder.imgPosterPath);



    }

    public void setOnClickListener(IMovieOnItemListener movieOnClickListener){
        this.movieOnClickListener = movieOnClickListener;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgPosterPath;
        TextView titleMovie;
        TextView voteAverage;
        TextView releaseDate;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPosterPath = itemView.findViewById(R.id.imgPoster);
            titleMovie = itemView.findViewById(R.id.txtTitleMovie);
            voteAverage = itemView.findViewById(R.id.txtVoteAverage);
            releaseDate = itemView.findViewById(R.id.txtReleaseDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (movieOnClickListener!= null){
                movieOnClickListener.onClickListener(v,getAdapterPosition());
            }
        }
    }


}

