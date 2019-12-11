package com.example.moviefinden.features.searchmovie.searchmovieadapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.moviefinden.models.ResultSearch;

public class MovieListDiffutils extends DiffUtil.ItemCallback<ResultSearch> {
    @Override
    public boolean areItemsTheSame(@NonNull ResultSearch oldItem, @NonNull ResultSearch newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull ResultSearch oldItem, @NonNull ResultSearch newItem) {
        return oldItem.getOriginalTitle().equals(newItem.getOriginalTitle());
    }
}
