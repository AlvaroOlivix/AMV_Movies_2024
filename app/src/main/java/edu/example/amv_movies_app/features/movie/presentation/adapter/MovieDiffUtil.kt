package edu.example.amv_movies_app.features.movie.presentation.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import edu.example.amv_movies_app.features.movie.domain.Movie

class MovieDiffUtil : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}