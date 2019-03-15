package com.example.myapplication.main.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myapplication.main.data.model.Movie;
import com.example.myapplication.main.data.remote.FailureListener;
import com.example.myapplication.main.data.remote.MovieRepository;
import com.example.myapplication.main.data.remote.SuccessListener;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MutableLiveData<List<Movie>> _items = new MutableLiveData<>();
    public LiveData<List<Movie>> items = _items;

    private MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public LiveData<Boolean> loading = _loading;

    private MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    public void getUpcomingMovies() {
        _loading.setValue(true);
        movieRepository.fetchUpcomingMovies(new SuccessListener() {
            @Override
            public void onSuccess(List<Movie> movies) {
                _loading.setValue(false);
                _items.setValue(movies);
            }
        }, new FailureListener() {
            @Override
            public void onFailure(String error) {
                _loading.setValue(false);
                _error.setValue(error);
            }
        });
    }
}
