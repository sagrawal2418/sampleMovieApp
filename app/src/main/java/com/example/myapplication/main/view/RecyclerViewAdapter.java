package com.example.myapplication.main.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.main.UIUtils;
import com.example.myapplication.main.data.model.Movie;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder> {

    private List<Movie> dataSet;

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        final ImageView movieImageView;
        final TextView movieNameTextView;
        final TextView releaseDateTextView;
        final TextView movieOverviewTextView;

        MovieViewHolder(View view) {
            super(view);

            movieImageView = view.findViewById(R.id.movie_image);
            movieNameTextView = view.findViewById(R.id.movie_name);
            releaseDateTextView = view.findViewById(R.id.release_date);
            movieOverviewTextView = view.findViewById(R.id.movie_overview);
        }

        private void setMovie(Movie movie) {
            movieNameTextView.setText(movie.getOriginalTitle());
            releaseDateTextView.setText(movie.getReleaseDate());
            movieOverviewTextView.setText(movie.getOverview());

            String imagePath = movie.getPosterPath();
            UIUtils.loadImageIntoView(imagePath, movieImageView);
        }
    }

    public RecyclerViewAdapter(List<Movie> myDataSet) {
        dataSet = myDataSet;
    }

    public void replaceList(List<Movie> movies) {
        this.dataSet = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MovieViewHolder holder, int position) {
        holder.setMovie(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }
}
