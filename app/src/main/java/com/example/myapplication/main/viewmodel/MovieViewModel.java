package com.example.myapplication.main.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.myapplication.main.data.model.Movie;
import com.example.myapplication.main.data.remote.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private final MutableLiveData<List<Movie>> _items = new MutableLiveData<>();
    public final LiveData<List<Movie>> items = _items;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public final LiveData<Boolean> loading = _loading;

    private final MutableLiveData<String> _error = new MutableLiveData<>();
    public LiveData<String> error = _error;

    public void getUpcomingMovies() {
        _loading.setValue(true);
        movieRepository.fetchUpcomingMovies(movies -> {
            _loading.setValue(false);
            _items.setValue(movies);
        }, error -> {
            _loading.setValue(false);
            _error.setValue(error);
        });
    }
}
