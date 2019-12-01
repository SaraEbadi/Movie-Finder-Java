package com.example.moviefinden.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieModel {
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<ResultSearch> resultSearchList;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<ResultSearch> getResultSearchList() {
        return resultSearchList;
    }

    public void setResultSearchList(List<ResultSearch> resultSearchList) {
        this.resultSearchList = resultSearchList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
