package com.example.myapplication.main.data.remote;

import com.example.myapplication.main.data.model.Movie;

import java.util.List;

public interface SuccessListener {

    void onSuccess(List<Movie> movies);
}