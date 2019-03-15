package com.example.myapplication.main.data.model;

import com.google.gson.annotations.SerializedName;

import static com.example.myapplication.main.Constants.MOVIE_BASE_URL;


public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;

    public String getPosterPath() {
        return MOVIE_BASE_URL + posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
