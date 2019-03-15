package com.example.myapplication.main.data.remote.retrofit;

import com.example.myapplication.BuildConfig;
import com.example.myapplication.main.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("movie/upcoming?api_key=" + BuildConfig.API_KEY + "&language=en-US&page=1")
    Call<MovieResponse> getUpcomingMovies();
}