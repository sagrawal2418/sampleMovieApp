package com.example.myapplication.main.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.R;
import com.example.myapplication.main.data.model.Movie;
import com.example.myapplication.main.viewmodel.MovieViewModel;

import java.util.List;

import kotlin.Lazy;

import static org.koin.java.standalone.KoinJavaComponent.inject;


public class MainActivity extends AppCompatActivity {

    private final Lazy<MovieViewModel> viewModel = inject(MovieViewModel.class);
    private RecyclerViewAdapter recyclerViewAdapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        setupRecyclerView();

        viewModel.getValue().items.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                recyclerViewAdapter.replaceList(movies);
            }
        });

        viewModel.getValue().loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                progressBar.setVisibility(aBoolean != null && aBoolean ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.getValue().getUpcomingMovies();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.movies_recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(null);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}