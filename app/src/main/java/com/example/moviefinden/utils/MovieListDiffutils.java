package com.example.moviefinden.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.moviefinden.service.model.ResultSearch;

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
