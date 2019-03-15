package com.example.myapplication.main.data.remote;

public interface MovieRepository {

    void fetchUpcomingMovies(SuccessListener successListener, FailureListener failureListener);
}
