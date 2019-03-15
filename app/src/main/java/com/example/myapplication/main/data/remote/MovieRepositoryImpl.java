package com.example.myapplication.main.data.remote;

import com.example.myapplication.main.data.model.Movie;
import com.example.myapplication.main.data.model.MovieResponse;
import com.example.myapplication.main.data.remote.retrofit.MovieApi;
import com.example.myapplication.main.data.remote.retrofit.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepositoryImpl implements MovieRepository {

    private final RetrofitClient retrofitClient;

    public MovieRepositoryImpl(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    @Override
    public void fetchUpcomingMovies(final SuccessListener successListener, final FailureListener failureListener) {
        MovieApi service = retrofitClient.createRetrofit().create(MovieApi.class);
        Call<MovieResponse> call = service.getUpcomingMovies();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        notifySuccess(successListener, response.body().getMovies());
                    } else {
                        notifyFailure(failureListener, "Unexpected Response");
                    }
                } else {
                    notifyFailure(failureListener, "Unexpected Response");
                }
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                notifyFailure(failureListener, t.getLocalizedMessage());
            }
        });
    }

    private void notifySuccess(SuccessListener successListener, List<Movie> list) {
        if (successListener != null) {
            successListener.onSuccess(list);
        }
    }

    private void notifyFailure(FailureListener failureListener, String error) {
        if (failureListener != null) {
            failureListener.onFailure(error);
        }
    }
}
