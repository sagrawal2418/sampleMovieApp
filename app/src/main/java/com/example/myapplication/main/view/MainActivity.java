package com.example.myapplication.main.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.main.viewmodel.MovieViewModel;

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

        viewModel.getValue().items.observe(this, movies -> recyclerViewAdapter.replaceList(movies));

        viewModel.getValue().loading.observe(this, aBoolean -> progressBar.setVisibility(aBoolean != null && aBoolean ? View.VISIBLE : View.GONE));

        viewModel.getValue().error.observe(this, s -> Toast.makeText(MainActivity.this,
                s, Toast.LENGTH_LONG).show());

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